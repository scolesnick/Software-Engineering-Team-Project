package client;

import client.CreateAccountControl;
import client.LoginControl;
import gameMechanics.Player;
import ocsf.client.AbstractClient;

public class GameClient extends AbstractClient
{
	private Player player;
	private LoginControl loginCtrl;
	private CreateAccountControl createCtrl;
	private JoinGameControl joinCtrl;
	private GameControl gameCtrl;
	
  public GameClient()
  {
	  super("localhost",8300);
  }
  
  public void setCreateController(CreateAccountControl cc) 
  {
	  createCtrl = cc;
  }
  
  public void setLoginController(LoginControl lc) 
  {
	  loginCtrl = lc;
  }

  public void handleMessageFromServer(Object arg0)
  {
	  System.out.println("Server Message sent to Client " + (String)arg0);
	  if (arg0.equals("true"))
		  loginCtrl.loginSuccess();
	  else if (arg0.equals("false"))
		  loginCtrl.displayError("Invalid login credentials.");
	  
	  if (arg0.equals("valid"))
		  createCtrl.createSuccess();
	  else if (arg0.equals("duplicate"))
		  createCtrl.displayError("Duplicate username. ");
	  else if (arg0.equals("mathching"))
		  createCtrl.displayError("Passwords don't match. ");
	  else if (arg0.equals("longpass"))
		  createCtrl.displayError("Password too long. ");
	  else if (arg0.equals("shortpass"))
		  createCtrl.displayError("Password too short. ");
	  }
}
  