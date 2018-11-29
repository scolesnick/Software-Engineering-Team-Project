package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JoinGameControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	
	// Constructor
	public JoinGameControl(JPanel container, GameClient client)
	{
		this.container = container;
		this.client = client;
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
			displayGamePanel();
		}
		
	}
	
}
