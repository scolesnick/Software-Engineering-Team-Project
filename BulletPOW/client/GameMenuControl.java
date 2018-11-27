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
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "4");
	}
	
	public void displayLoginPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "1");
	}
	
	public void displayGamePanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "5");
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
			displayGamePanel();
		}
		
	}

}
