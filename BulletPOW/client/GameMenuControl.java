package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ocsf.server.ConnectionToClient;
import server.GameServer;
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
	
	public void displayJoinGamePanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "join");
	}
	
	public void displayLoginPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "login");
	}
	
	public void displayGamePanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "game");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command == "Logout") 
		{
			displayLoginPanel();
		}
		else if(command == "Join Game")
		{
			displayJoinGamePanel();
		}
		else if(command == "Host Game")
		{
			
			try {
				client.sendToServer(ServerMessage.HostGame);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			displayGamePanel();
		}
		
	}

}
