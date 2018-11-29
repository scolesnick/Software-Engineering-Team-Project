package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import messageData.LoginData;
import messageData.User;
import messageData.CreateAccountData;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	
	
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
	
	private ArrayList<gameMechanics.GameInfo> gamelist;
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
		  }
		  catch(IOException e) 
		  {
			  e.printStackTrace();
			  System.out.println(e.getMessage());
			  
		  }
		  
		  System.out.println("Message from Client" + msg.toString() + client.toString() + "\n");   
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
  System.out.println("Client Connected");
}
}

