package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
		
	}
	
	public void displayLoginPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "1");
	}
	
	public void displayGamePanel()
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
		
	}

}
