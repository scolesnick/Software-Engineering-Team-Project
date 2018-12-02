package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import server.ServerMessage;

public class GameMenuControl implements ActionListener
{
	private JPanel container;
	private JLabel status;
	private GameClient client;

	// Constructor
	public GameMenuControl(JPanel container, GameClient gc)
	{
		this.client = gc;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		if (command == "Logout")
		{
			client.displayLoginPanel();
		} 
		else if (command == "Join Game")
		{
			client.displayJoinGamePanel();
		} 
		else if (command == "Host Game")
		{
			try
			{
				client.sendToServer(ServerMessage.HostGame);
			} catch (IOException e1){e1.printStackTrace();}
			
			client.displayGamePanel();
		}
	}

	public void displayGameMenuPanel()
	{
		CardLayout cLayout = (CardLayout) container.getLayout();
		cLayout.show(container, "menu");
	}
}