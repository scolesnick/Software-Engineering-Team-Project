package server;

import java.io.IOException;
import java.util.ArrayList;

import database.Database;
import messageData.LoginData;
import messageData.User;
import messageData.CreateAccountData;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	
	private ConnectionToClient player1 = null;
	private ConnectionToClient player2 = null;
	private ArrayList<gameMechanics.GameInfo> gamelist;
	private Database database;
	
	public static void main(String args[]) {
		
		GameServer server = new GameServer();
		
		try {server.listen();System.out.println("Server started on port 8300");} catch (IOException e) {e.printStackTrace();System.exit(0);}
		
	}
	
	public GameServer() {
		
		this(8300);
		
	}
	
	public GameServer(int port) {
		
		super(port);
	    this.setTimeout(500);
	    database = new Database();
	    
	}
	
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		
		try {
			if(msg instanceof LoginData) {
				
				LoginData loginData = (LoginData) msg;
				User user = database.getUser((LoginData)msg);
				
				if(user != null)
					client.sendToClient(user);
				else
					client.sendToClient(ServerMessage.InvalidLogin);  
				
			}
			else if (msg instanceof CreateAccountData) {
				client.sendToClient(database.createAccount((CreateAccountData)msg));
				
			}
			
		} catch(IOException e){e.printStackTrace();System.out.println(e.getMessage());}
		
		System.out.println("Message from Client" + msg.toString() + client.toString() + "\n");
		
	}
	
	protected void listeningException(Throwable exception) {
		
		//Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());
		
	}
	
	protected void serverStarted() {
		
		System.out.println("Server Started");
		
	}
	
	protected void serverStopped() {
		
		System.out.println("Server Stopped");
		
	}
	
	protected void serverClosed() {
		
		System.out.println("Server and clients are closed - Press Listen to Restart");
		
	}
	
	protected void clientConnected(ConnectionToClient client) {
		
		if ( (player1 != null) && (player2 != null) ) {
			
			System.out.println("Game is full.");
			try {client.close();}catch(IOException e) {System.out.println("error closing 3rd client");}
			
			return;
			
		}
		client.getInfo(null);
		if (player1 == null)
			player1 = client;
		else
			player2 = client;
		
		System.out.println(player1 + "  :  " + player2);
		
	}
	
	protected void clientDisconnected(ConnectionToClient client) {
		
		
		
	}
}

