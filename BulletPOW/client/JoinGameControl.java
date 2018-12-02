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
	
	public void displayMenuPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "menu");
	}
	public void displayGamePanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "game");
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String command = arg0.getActionCommand();
		
		if(command == "Back")
		{
			displayMenuPanel();
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
			
			displayGamePanel();
		}
		
	}
	
	public void updateGameList(ArrayList<String> gameList)
	  {
	    JoinGamePanel joinPanel = (JoinGamePanel)container.getComponent(2);
	    joinPanel.updateGameList(gameList);
	    
	  }
	
}
