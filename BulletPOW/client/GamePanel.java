package client;

import java.awt.BorderLayout;

import javax.swing.*;

public class GamePanel extends JPanel
{
	private JButton logOut;
	private JLabel status;
	private JButton exitGame;
	
	// Constructor
	public GamePanel(GameControl gc)
	{
		// panel for status at top
		JPanel topPanel = new JPanel();
		status = new JLabel("Waiting for another player");
		topPanel.add(status);
		
		// panel for buttons at the bottom
		JPanel botPanel = new JPanel();
		logOut = new JButton("Log Out");
		exitGame = new JButton("Exit Game");
		logOut.addActionListener(gc);
		exitGame.addActionListener(gc);
		botPanel.add(logOut, BorderLayout.EAST);
		botPanel.add(exitGame, BorderLayout.WEST);
		
		// panel for game
		JPanel game = new JPanel();
		JTextArea deleteLater = new JTextArea();
		deleteLater.setText("test for design\ntest for design\ntest for design\ntest for design\n");
		game.add(deleteLater);
		
		// panel for design
		JPanel grid = new JPanel(new BorderLayout());
		grid.add(topPanel, BorderLayout.NORTH);
		grid.add(game, BorderLayout.CENTER);
		grid.add(botPanel, BorderLayout.SOUTH);
		this.add(grid);
	}

}
