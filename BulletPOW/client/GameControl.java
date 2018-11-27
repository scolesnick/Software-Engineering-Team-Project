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
		cLayout.show(container, "1");
	}
	public void displayMenuPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "3");
	}
	public void applyBuff()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String command = arg0.getActionCommand();
		
		if(command == "Log Out")
		{
			displayLoginPanel();
		}
		else if(command == "Exit Game")
		{
			displayMenuPanel();
		}
		
	}

}
