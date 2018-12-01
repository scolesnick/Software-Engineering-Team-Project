package client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameGUI extends JFrame
{


	private GameClient client; 
	final public static int DEFAULT_PORT = 8300;

	// Constructor that creates the client GUI.
	public GameGUI(String host, int port)
	{
		

		try {
			client = new GameClient(host, port);
			client.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Set the title and default close operation.
		this.setTitle("Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the card layout container.
		CardLayout cardLayout = new CardLayout();
		JPanel container = new JPanel(cardLayout);

		//Create the Controllers
		LoginControl lc = new LoginControl(container, client);
		CreateAccountControl cc = new CreateAccountControl(container, client);
		JoinGameControl jgc = new JoinGameControl(container, client);
		GameMenuControl gmc = new GameMenuControl(container, client);
		GameControl gc = new GameControl(container, client);
		
		client.setCreateController(cc);
		client.setLoginController(lc);
		client.setJoinGameController(jgc);
		client.setGameControl(gc);

		
		// Create the views
		JPanel login = new LoginPanel(lc);
		JPanel create = new CreateAccountPanel(cc);
		JPanel join_game = new JoinGamePanel(jgc);
		JPanel game_menu = new GameMenuPanel(gmc);
		JPanel game = new GamePanel(gc);


		// Add the views to the card layout container.
		container.add(login, "login");
		container.add(create, "create");
		container.add(join_game, "join");
		container.add(game_menu, "menu");
		container.add(game, "game");


		// Show the initial view in the card layout.
		cardLayout.show(container, "login");

		// Add the card layout container to the JFrame.
		this.add(container, BorderLayout.CENTER);

		// Show the JFrame.
		this.setSize(800,800);
		this.setVisible(true);

	}

	// Main function that creates the client GUI when the program is started.
	public static void main(String[] args)
	{
		{
		    String host = "";
		    int portNumber = 0; 
		    
		    
		    try
		    {
		      host = args[0];
		    }
		    catch(ArrayIndexOutOfBoundsException e)
		    {
		      host = "localhost";
		    }
		    
		    try
		    {
		      portNumber = Integer.parseInt(args[1]);
		    }
		    catch(ArrayIndexOutOfBoundsException e)
		    {
		      portNumber = DEFAULT_PORT;
		    }
		    
		    
		    new GameGUI(host,portNumber);
	}
}
}
