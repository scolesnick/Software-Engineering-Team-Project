package client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{
	private static int WIDTH = 500;
	private static int HEIGHT = 500;
	
	private ImageIcon image;
	private Timer timer;
	private int delay = 1000/60; //milliseconds
	private int x=20, y=30;
	private int player_speed = 5;
	
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	
	private JButton logOut;
	private JLabel status;
	private JButton exitGame;
	private JPanel game;
	
	// Constructor
	public GamePanel(GameControl gc) {
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(Color.blue);
		try
		{
			image = new ImageIcon(ImageIO.read(new File("../BulletPOW/guy1.jpg")));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init() {
		timer = new Timer(delay, this);
		timer.setActionCommand("Timer");
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(this);
		timer.start();
	}
//	public GamePanel(GameControl gc)
//	{
//		// panel for status at top
//		JPanel topPanel = new JPanel(new FlowLayout());
//		status = new JLabel("Waiting for another player");
//		topPanel.add(status);
//		
//		// panel for buttons at the bottom
//		JPanel botPanel = new JPanel();
//		logOut = new JButton("Log Out");
//		exitGame = new JButton("Exit Game");
//		logOut.addActionListener(gc);
//		exitGame.addActionListener(gc);
//		botPanel.add(logOut, BorderLayout.EAST);
//		botPanel.add(exitGame, BorderLayout.WEST);
//		
//		// panel for game
//		Map();
//		this.add(game);
//		// panel for design
////		JPanel grid = new JPanel(new BorderLayout());
////		grid.add(topPanel, BorderLayout.NORTH);
////		grid.add(game, BorderLayout.CENTER);
////		grid.add(botPanel, BorderLayout.SOUTH);
////		this.add(grid);
//	}
	
//	public void Map() {
//		game = new JPanel();
//		game.setSize(WIDTH, HEIGHT);
//		game.setBackground(Color.blue);
//		
//		timer = new Timer(delay, this);
//		timer.setActionCommand("Timer");
//		try
//		{
//			image = new ImageIcon(ImageIO.read(new File("../BulletPOW/guy1.jpg")));
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		game.setFocusable(true);
//		game.addKeyListener(this);
//		timer.start();
//	}
	
	public void paintComponent (Graphics page) {
		   super.paintComponent (page);
		   image.paintIcon (this, page, y, x);
		}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		System.out.println("pressed");
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_S) {
			down = true;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}		
	}

	@Override
	public void keyReleased(KeyEvent arg0){
		System.out.println("released");
		if(arg0.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0){	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if(command == "Timer") {
//			System.out.println("Timer ticked");
			if(up) {
				if(x-player_speed > 0) {
					x -= player_speed;
				}
			}
			if(down) {
				if(x+player_speed < HEIGHT - image.getIconHeight()) {
					x += player_speed;
				}
			}
			if(left) {
				if(y-player_speed > 0) {
					y -= player_speed;
				}
			}
			if(right) {
				if(y+player_speed < WIDTH - image.getIconWidth()) {
					y += player_speed;
				}
			}
			repaint();
		}
		
	}

}
