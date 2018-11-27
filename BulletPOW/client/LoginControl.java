package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class LoginControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	
	public LoginControl(JPanel container, GameClient gc)
	{
		this.container = container;
		this.client = gc;
	}

	public void loginSuccess()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "3");
	}
	
	public void createAccount()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "2");
	}
	
	public void displayError(String error)
	{
		LoginPanel loginPanel  =(LoginPanel)container.getComponent(0);
		loginPanel.setError(error);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		 // Get the name of the button clicked.
	    String command = arg0.getActionCommand();
	    
	    if (command == "Create")
	    {
	    	createAccount();
	    }	    
	    else if (command == "Submit")
	    {
	    	// TODO change this to check validity of user/psw before loginSuccess()
	    	loginSuccess();
	    }

		
	}

}
