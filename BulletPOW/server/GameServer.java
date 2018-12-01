package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

import client.ClientNode;
import client.GameControl;
import client.GameMenuControl;
import client.JoinGameControl;
import database.Database;
import messageData.LoginData;
import messageData.User;
import messageData.CreateAccountData;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	
	
	
	ClientNode newClient;
	
	public static void main(String args[]) 
	{
		GameServer server = new GameServer();
		try {
			server.listen();
			System.out.println("Server started on port 8300");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	private ArrayList<ClientNode> clientList;
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
	    clientList = new ArrayList<ClientNode>();
	    
	  }
	  
	  protected void handleMessageFromClient(Object msg, ConnectionToClient client)
	  {
	    
		  try 
		  {
			  if(msg instanceof LoginData) 
			  {
				  LoginData loginData = (LoginData) msg;
				  
				  User user = database.getUser((LoginData)msg);
				  if(user != null)
					  client.sendToClient(user);
				  else 
					  client.sendToClient(ServerMessage.InvalidLogin);  
			  }
			  else if (msg instanceof CreateAccountData) 
			  {
				  client.sendToClient(database.createAccount((CreateAccountData)msg));
			  }
			  else if (msg instanceof String)
			  {
				  hostButtonPushed(client);
				  sendToAllClients(msg);
			  }
			 
		  }
		  catch(IOException e) 
		  {
			  e.printStackTrace();
			  System.out.println(e.getMessage());
			  
		  }
		  
		  System.out.println("Message from Client" + msg.toString() + " " + client.toString() + "\n");   
	  }
		
	

protected void listeningException(Throwable exception) 
{
  //Display info about the exception
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


protected void clientConnected(ConnectionToClient client) 
{
  newClient = new ClientNode(client.getId());
  System.out.println("Client Connected");
  
}
private void hostButtonPushed(ConnectionToClient client)
{
	
	System.out.println(client.getId());
	System.out.println(newClient.getName());
	
	// Adding client to the client list
	  	clientList.add(newClient);
	  	
	  	sendToAllClients(newClient.getName());
	  	
	  	client.setInfo("ID", newClient.getName());
	  		
	  		
	  		try {
	  			ConnectionToClient opponent = findOpponent(client);
	  			
	  			opponent.sendToClient("You have a new opponent!");
				client.sendToClient("You have a new opponent!");
				
				System.out.println("[INFO] Client " + client.toString() + " has the opponent "+opponent.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		
	  	
	  
}
private ConnectionToClient findOpponent( ConnectionToClient client) throws NullPointerException
{
	Long opponentId = -1L; // Default value for a long
	
	ClientNode clientNode = new ClientNode(-999L);;
	
	for( ClientNode node : clientList )
	{// Iterate through all clients connected in the list
		
		// If listClient different from clientConnected and that client has himself as opponent
		if( node.getPlayerID() == client.getId())
		{
			clientNode = node; // find client node
	    	break;
		}
	}
	if (clientNode.getOpponentID() == clientNode.getPlayerID())
	{
  	for( ClientNode possibleOpponentNode : clientList )
  	{// Iterate through all clients connected in the list
  		
  		// If listClient different from clientConnected and that client has himself as opponent
  		if( possibleOpponentNode.getPlayerID() != clientNode.getPlayerID() && possibleOpponentNode.getPlayerID() == possibleOpponentNode.getOpponentID())
  		{
  			// Sets the client's opponent's opponent as himself
  			opponentId = possibleOpponentNode.getPlayerID();
  			possibleOpponentNode.setOpponentID(client.getId());
  			clientNode.setOpponentID(opponentId);
  	    	break;
  		}
  	}
	}
	else
		opponentId = clientNode.getOpponentID();
	
	// Iterate through the connectiontoclients array
	if(opponentId != -1L)
	{
		for(Thread clientThread : getClientConnections())// list of connections
		{
			if(clientThread.getId() == opponentId)
			{
				return (ConnectionToClient) clientThread;
			}
		}
	}
	else
		throw new NullPointerException("Client has no opponent.");
	
	return null;
}

}

