package client;

import javax.swing.*;

import messageData.GameActionData;

import java.awt.CardLayout;
import java.awt.event.*;

public class GameControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	private JLabel status;
	
	public GameControl(JPanel container, GameClient client)
	{
		this.client = client;
		this.container = container;
	}

	
	/*
	 * GameMap mechanics updated here
	 */
	public void update(GameActionData gameData) 
	{
		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();
	}
	
	public void displayLoginPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "login");
	}
	public void displayMenuPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "menu");
	}
	public void applyBuff()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command == "Logout")
		{
			displayLoginPanel();
		}
		else if(command == "Exit Game")
		{
			displayMenuPanel();
		}
		
		
		
	}
	
	public void updateStatus(String updateStatus)
	  {
	    GamePanel gamePanel = (GamePanel)container.getComponent(4);
	    gamePanel.updateStatus(updateStatus);
	    
	  }

}
