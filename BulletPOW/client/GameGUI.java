package client;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameGUI extends JFrame
{
	private GameClient client;
	
	// Constructor
	public GameGUI()
	{
		client = new GameClient();
		this.setTitle("Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the card layout container.
	    CardLayout cardLayout = new CardLayout();
	    JPanel container = new JPanel(cardLayout);
	    
	    // Create the Controllers
	    
	    // Create the different views
//	    JPanel view1 = new InitialPanel(ic);
//	    JPanel view2 = new LoginPanel(lc);
	    //etc.
	    
	    // Add the views to the card layout container
//	    container.add(view1, "1");
//	    container.add(view2, "2");
	    // etc.
	    // Show the initial view in the card layout
	    cardLayout.show(container, "1");
	    
	    // Add the card layout container to the JFrame
	    this.add(container, BorderLayout.CENTER);
	    
	    // Show JFrame
	    this.setSize(550, 350);
	    this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new GameGUI();
	}

}