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
	    GameMenuControl gmc = new GameMenuControl(container, client);
	    GameControl gc = new GameControl(container, client);
	    JoinGameControl jgc = new JoinGameControl(container, client);
	    LoginControl lc = new LoginControl(container, client);
	    CreateAccountControl cac = new CreateAccountControl(container, client);
	    
	    // Create the different views
	    JPanel view1 = new LoginPanel(lc);
	    JPanel view2 = new CreateAccountPanel(cac);
	    JPanel view3 = new GameMenuPanel(gmc);
	    JPanel view4 = new JoinGamePanel(jgc);
	    JPanel view5 = new GamePanel(gc);

	    // Add the views to the card layout container
	    container.add(view1, "1");
	    container.add(view2, "2");
	    container.add(view3, "3");
	    container.add(view4, "4");
	    container.add(view5, "5");

	    // Show the login view in the card layout initially
	    cardLayout.show(container, "1");
//	    cardLayout.show(container, "5");
	    
	    // Add the card layout container to the JFrame
	    this.add(container, BorderLayout.CENTER);
	    
	    // Show JFrame
	    this.setSize(500, 500);
	    this.setVisible(true);
	}

	public static void main(String[] args)
	{
		new GameGUI();
	}

}
