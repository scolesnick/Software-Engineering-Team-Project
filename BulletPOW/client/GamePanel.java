package client;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel 
{
	private static int WIDTH = 800;
	private static int HEIGHT = 800;
	
	private JButton logOut;
	private JButton exitGame;
	private JLabel status;
	private JPanel game;
	private JPanel top;
	private JPanel bot;
	private JPanel left;
	private JPanel right;
	
	public void updateStatus(String playerC)
	  {
	    status.setText(playerC);
	  }
	
	// Constructor
	public GamePanel(GameControl gc) {
		this.setLayout(new BorderLayout());
		
		// top panel
		top = new JPanel();
		status = new JLabel("Waiting for Player");
		status.setForeground(Color.WHITE);
		top.add(status);
		top.setPreferredSize(new Dimension(WIDTH, 40));
		top.setBackground(Color.DARK_GRAY);
		
		// bot panel
		bot = new JPanel(new BorderLayout());
		logOut = new JButton("Logout");
		exitGame = new JButton("Exit Game");
		bot.add(logOut, BorderLayout.EAST);
		bot.add(exitGame, BorderLayout.WEST);
		bot.setPreferredSize(new Dimension(WIDTH,  40));
		bot.setBackground(Color.DARK_GRAY);
		
		// side panels
		left = new JPanel();
		right = new JPanel();
		Dimension dim = new Dimension(25, HEIGHT - 80);
		left.setPreferredSize(dim);
		right.setPreferredSize(dim);		
		left.setBackground(Color.DARK_GRAY);
		right.setBackground(Color.DARK_GRAY);
		
		// game panel
		game = new GameMap();
		game.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT - 80));
		game.setBackground(Color.BLUE);
		game.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3, true));
		
		// add panels
		this.add(top, BorderLayout.NORTH);
		this.add(game, BorderLayout.CENTER);
		this.add(left, BorderLayout.WEST);
		this.add(right, BorderLayout.EAST);
		this.add(bot, BorderLayout.SOUTH);
		
		logOut.addActionListener(gc);
		exitGame.addActionListener(gc);
	}	
}
