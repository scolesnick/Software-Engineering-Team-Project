package client;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel implements ActionListener
{
	private ImageIcon image;
	private Timer timer;
	private int delay = 1000/60; //milliseconds
	private int x=20, y=30;
	private int player_speed = 5;
	
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	

	
	public GameMap() {
//		image = new ImageIcon(this.getClass().getResource("/guy1.jgp"));
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
				if(y+player_speed < this.getHeight() - image.getIconHeight()) {
					y += player_speed;
				}
			}
			if(left) {
				if(x-player_speed > 0) {
					x -= player_speed;
				}
			}
			if(right) {
				if(x+player_speed < this.getWidth() - image.getIconWidth()) {
					x += player_speed;
				}
			}
			up=down=left=right=false;
			repaint();
		}
	}
}
