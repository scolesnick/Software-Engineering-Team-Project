	package server;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;
import gameMechanics.*;
import messageData.*;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer 
{
	private ArrayList<GameInfo> gameList;
	private Database database;

	public GameServer() 
	{
		this(8300);
	}

	public GameServer(int port)
	{
		super(port);
		this.setTimeout(500);
		database = new Database();
		gameList = new ArrayList<GameInfo>();
	}

	protected void handleMessageFromClient(Object msg, ConnectionToClient client) 
	{
		try {
			if (msg instanceof LoginData) 
			{
				LoginData loginData = (LoginData) msg;

				User user = database.getUser((LoginData) msg);
				if (user != null)
					client.sendToClient(user);
				else
					client.sendToClient(ServerMessage.InvalidLogin);
			} 
			else if (msg instanceof CreateAccountData) 
			{
				client.sendToClient(database.createAccount((CreateAccountData) msg));
			}
			else if(msg instanceof JoinGameData) 
			{
				GameInfo game = getGame(((JoinGameData) msg).getGameName());
				
				if(game.getGuestID() == null) 
				{
					game.setGuest(client);
					client.sendToClient(ServerMessage.JoinGameSuccess);
					game.startGame();
				}
				else 
				{
					client.sendToClient(ServerMessage.GameAlreadyInPlay);
				}
				
			}
			else if (msg instanceof GameActionData) 
			{
				GameInfo game = findGame(client);
				
				if(game != null && game.getHostID() == client.getId()) 
				{
					game.getHost().setX(((GameActionData) msg).getPx());
					game.getHost().setY(((GameActionData) msg).getPy());	
				}
				else if (game != null && game.getGuestID() == client.getId()) 
				{
					game.getGuest().setX(((GameActionData) msg).getPx());
					game.getGuest().setY(((GameActionData) msg).getPy());
				}
				
			}
			else if(msg instanceof MouseClickData) 
			{
				GameInfo game = findGame(client);
				game.newBullet(((MouseClickData) msg).getMouseX(), ((MouseClickData) msg).getMouseY(), client);
			}
			else if(msg instanceof ServerMessage) 
			{
				switch ((ServerMessage)msg) {
				//Calls for when client requests a list of games
				case GameListUpdate:
					ArrayList<String> gameNameList = new ArrayList<>();
					for(GameInfo g : gameList) {gameNameList.add(g.getGameName());}
					client.sendToClient(new JoinGameData(gameNameList));
					break;
				case HostGame:
					GameInfo newGame = new GameInfo(client.getName(), client);
					gameList.add(newGame);
					client.sendToClient(ServerMessage.HostGameSuccess);
					break;
				default:
					break;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	protected void listeningException(Throwable exception) 
	{
		// Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());
	}

	protected void serverStarted() 
	{
		System.out.println("Server Started");
	}

	protected void serverStopped() 
	{
		System.out.println("Server Stopped");
	}

	protected void serverClosed() 
	{
		System.out.println("Server and clients are closed - Press Listen to Restart");
	}

	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		GameInfo game = findGame(client);
		game.stopGame();
		gameList.remove(game);
		System.out.println("Client Disconnected");
	}
	
	
	@Override
	protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
		GameInfo game = findGame(client);
		game.stopGame();
		gameList.remove(game);
		System.out.println("Client Disconnected");
	}
	
	
	
	protected void clientConnected(ConnectionToClient client) 
	{
		System.out.println("Client Connected");
	}
	
	private GameInfo getGame(String name) 
	{
		for(GameInfo game : gameList) 
		{
			if(game.getGameName().equals(name)) 
			{
				return game;
			}
		}
		return null;
	}
	
	private GameInfo findGame(ConnectionToClient client) 
	{
		for(GameInfo game : gameList) 
		{
			if(game.getHostID() == client.getId()) {return game;}
			else if(game.getGuest() != null && game.getGuestID() == client.getId()) {return game;}
		}
		return null;
	}


	public static void main(String args[]) 
	{
		GameServer server = new GameServer();
		try {
			server.listen();
			System.out.println("Server started on port 8300");
		} catch (IOException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}