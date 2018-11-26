package server;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import database.Database;
import messageData.LoginData;
import messageData.CreateAccountData;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	
	private ArrayList<gameMechanics.GameInfo> gamelist;
	private Database database;
	
	public GameServer()
	  {
	    super(8300);
	    this.setTimeout(500);
	    database = new Database();
	  }
	  
	  public GameServer(int port)
	  {
	    super(port);
	  }
	//ffdf
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		
		ArrayList<String> set = new ArrayList();
		  
		  if (arg0 instanceof CreateAccountData) {
			  
			  CreateAccountData createData = (CreateAccountData)arg0;

	          
	          int duplicate = 0;
	          int longpass = 0;
	          int shortpass = 0;
	          int mathching = 0;

	          String username = createData.getUsername();
	          String password = createData.getPassword();
	          String passv = createData.getVerifyPassword();

	          set = (database.query("select * from user where username='"+username+"';"));

	          if (set.isEmpty() == false) {
	        	  duplicate = 1;
	              try {
	                  arg1.sendToClient("duplicate");
	              } catch (IOException e) {e.printStackTrace();}
	          }

	          if (password.equals(passv) == false) {
	        	  mathching = 1;
	              try {
	                  arg1.sendToClient("mathching");
	              } catch (IOException e) {e.printStackTrace();}
	          }

	          if (password.length() < 6) {
	        	  shortpass = 1;
	              try {
	                  arg1.sendToClient("shortpass");
	              } catch (IOException e) {e.printStackTrace();}
	          }

	          if (password.length() > 16) {
	        	  longpass = 1;
	              try {
	                  arg1.sendToClient("longpass");
	              } catch (IOException e) {e.printStackTrace();}
	          }

	          if (duplicate == 0 && longpass == 0 && shortpass == 0 && mathching == 0) {

	              try {
	                    database.executeDML("insert into user values('"+createData.getUsername()+"',AES_ENCRYPT('"+createData.getPassword()+"','tanner'));");
	                    arg1.sendToClient("valid");
	                } catch (IOException e) {e.printStackTrace();}

	          }
			  
		  }
		  
		  else if (arg0 instanceof LoginData) {
			  LoginData loginData = (LoginData)arg0;
			 
	          set = database.query("select username,AES_DECRYPT(user.password,'tanner') from user where username='"+loginData.getUsername()+"';");
	          
	          
	          if (set.isEmpty() == false) {
	              if (loginData.getUsername().equals(set.get(0))) {
	                  if (loginData.getPassword().equals(set.get(1))) {
	                      try {
	                          arg1.sendToClient("true");
	                      } catch (IOException e) {e.printStackTrace();}
	                  }
	              }
	          }
	          else {

	              try {
	                  arg1.sendToClient("false");
	              } catch (IOException e) {e.printStackTrace();}}}
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


