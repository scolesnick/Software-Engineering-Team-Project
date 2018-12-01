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
			/**
			String hostName = "";
			try {
			     InetAddress addr = InetAddress.getLocalHost();
			     hostName = addr.getHostName();
			    } catch (UnknownHostException b) {b.printStackTrace();}
			client.setHost(hostName);
			client.setPort(8300);
			String hostInfo = client.getHost() +"          " + client.getPort();
			System.out.println(hostInfo);
			
			
			try {
				client.sendToServer(hostInfo);
				client.openConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			String hostPushed = "host button pushed";
			try {
				client.sendToServer(hostPushed);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			displayGamePanel();
		}
		
	}

}
