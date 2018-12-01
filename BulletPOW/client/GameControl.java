package client;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.event.*;

public class GameControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	private JLabel status;
//	private GameInfo game;
	
	public GameControl(JPanel container, GameClient client)
	{
		this.client = client;
		this.container = container;
	}
	
	public void checkCollision()
	{
		
	}
	public void addPlayer()
	{
		
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
