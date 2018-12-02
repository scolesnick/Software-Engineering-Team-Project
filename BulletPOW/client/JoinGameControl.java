package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import messageData.JoinGameData;

public class JoinGameControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	private JoinGamePanel panel;
	
	// Constructor
	public JoinGameControl(JPanel container, GameClient client)
	{
		this.container = container;
		this.client = client;
		//JoinGamePanel panel = (JoinGamePanel)container.getComponent(2);
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String command = arg0.getActionCommand();
		
		if(command == "Back")
		{
			client.displayGameMenuPanel();
		}
		else if(command == "Join")
		{
			JoinGamePanel panel = (JoinGamePanel)container.getComponent(2);
			try {
				client.sendToServer(new JoinGameData(panel.getSelectedValue()) );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			client.displayGamePanel();
		}
		
	}
	
	public void updateGameList(ArrayList<String> gameList)
	  {
	    JoinGamePanel joinPanel = (JoinGamePanel)container.getComponent(2);
	    joinPanel.updateGameList(gameList);
	    
	  }

	public void displayJoinGamePanel() {
		  CardLayout cardLayout = (CardLayout)container.getLayout();
	      cardLayout.show(container, "join");
		
	}
	
}
