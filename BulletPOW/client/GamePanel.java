package client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener
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
		
		addKeyBinding(this, false, KeyEvent.VK_W, "moveUp", (evt)->{up = true;});
		addKeyBinding(this, false, KeyEvent.VK_S, "moveDown", (evt)->{down = true;});
		addKeyBinding(this, false, KeyEvent.VK_A, "moveLeft", (evt)->{left = true;});
		addKeyBinding(this, false, KeyEvent.VK_D, "moveRight", (evt)->{right = true;});
		
		init();
	}
	
	private void addKeyBinding(JComponent comp, boolean release, int keyCode, String id, ActionListener actionListener) {
		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(keyCode, 0, release), id);
		ActionMap am = comp.getActionMap();
		am.put(id, new AbstractAction() {			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				actionListener.actionPerformed(e);
			}
		});
	}
	
	public void init() {
		timer = new Timer(delay, this);
		timer.setActionCommand("Timer");
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
		   image.paintIcon (this, page, x, y);
		}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		if(command == "Timer") {
//			System.out.println("Timer ticked");
			if(up) {
				if(y-player_speed > 0) {
					y -= player_speed;
				}
			}
			if(down) {
				if(y+player_speed < HEIGHT - image.getIconHeight()) {
					y += player_speed;
				}
			}
			if(left) {
				if(x-player_speed > 0) {
					x -= player_speed;
				}
			}
			if(right) {
				if(x+player_speed < WIDTH - image.getIconWidth()) {
					x += player_speed;
				}
			}
			up=down=left=right=false;
			repaint();
		}		
	}
}
