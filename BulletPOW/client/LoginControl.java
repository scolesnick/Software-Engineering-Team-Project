package client;

import java.awt.*;
import javax.swing.*;

import messageData.LoginData;

import java.awt.event.*;
import java.io.IOException;

public class LoginControl implements ActionListener
{
	// Private data fields for the container and game client.
	private JPanel container;
	private GameClient client;

	// Constructor for the login controller.
	public LoginControl(JPanel container, GameClient client)
	{
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Create button takes the user to the create account panel
		if (command == "Create")
		{
			client.displayCreateAccountPanel();
		}

		// The Submit button submits the login information to the server.
		else if (command == "Submit")
		{
			// Get the username and password the user entered.
			LoginPanel loginPanel = (LoginPanel) container.getComponent(0);
			LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());

			// Check the validity of the information locally first.
			if (data.getUsername().equals("") || data.getPassword().equals(""))
			{
				displayError("You must enter a username and password.");
				return;
			}

			// Submit the login information to the server.
			try
			{
				client.sendToServer(data);
			} catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}

	public void loginSuccess()
	{
		client.displayGameMenuPanel();
		displayError("");
	}

	public void displayError(String error)
	{
		LoginPanel loginPanel = (LoginPanel) container.getComponent(0);
		loginPanel.setError(error);

	}

	public void displayLoginPanel()
	{
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "login");
	}
}
