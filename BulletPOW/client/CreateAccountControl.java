package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JPanel;
import messageData.CreateAccountData;

public class CreateAccountControl implements ActionListener
{
	// Private data fields for the container and game client.
	private JPanel container;
	private GameClient client;

	// Constructor for the create controller.
	public CreateAccountControl(JPanel container, GameClient client)
	{
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Cancel button takes the user back to the initial panel.
		if (command == "Cancel")
		{
			client.displayLoginPanel();
		}

		// The Submit button submits the create information to the server.
		else if (command == "Submit")
		{
			// Get the username and password info the user entered.
			CreateAccountPanel createPanel = (CreateAccountPanel) container.getComponent(1);

			String username = createPanel.getUsername();
			String password = createPanel.getPassword();
			String password2 = createPanel.getPassword2();

			// Locally check for errors
			if (username.length() <= 0)
			{
				displayError("Please enter a username");
			} 
			else if (password.length() < 6)
			{
				displayError("Password needs to be at least 6 characters");
			} 
			else if (!(password.equals(password2)))
			{
				displayError("Passwords do not match.");
			} 
			else
			{
				CreateAccountData data = new CreateAccountData(username, password, password2);

				try
				{
					client.sendToServer(data);
				} catch (IOException e){e.printStackTrace();}
			}
		}
	}

	// After the creation is successful, go back to initial panel
	public void createSuccess()
	{
		client.displayLoginPanel();
	}

	public void displayError(String error)
	{
		CreateAccountPanel panel = (CreateAccountPanel) container.getComponent(1);
		panel.setError(error);
	}

	public void displayCreateAccountPanel()
	{
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "create");
	}
}