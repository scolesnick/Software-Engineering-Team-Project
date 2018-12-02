package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.*;

public class GameMap extends JPanel implements ActionListener,MouseListener{
	
	//dude image stuff
	private BufferedImage dude;
	private Image dudeImage;
	private int x=0, y=0, player_speed=5;
	
	
	//objects
	private gameMechanics.Player me;
	
	private gameMechanics.Player other;
	
	private gameMechanics.Bullets bulletObject;
	
	private gameMechanics.Bullets otherBulletObject;

	//timer creation
	private Timer timer;
	
	//keybinding flags
	private boolean dPressed, aPressed, sPressed, wPressed, spPressed, bulletShot = false, mouseClicked = false;
	
	//mouse click stuff
	private int mousex, mousey;
	
	//bullet stuff
	Rectangle bullet;
	private int bx=0, by=0, bspeed=10;
	double bulletV = 2.0;
	double angle, xVel, yVel;
	
	Rectangle otherBullet;
	private int otherbx=0, otherby=0, otherbspeed=10;
	double otherAngle, otherxVel, otheryVel;
	private boolean otherBulletShot;
	
	public GameMap() {
		
		//creates a buffered image from the jpg stored above the package
		try {dude = ImageIO.read(new File("guy1.jpg"));} catch (IOException ex) {}
		
		setBackground(Color.BLACK);
		
		//creates an Image based on the BufferedImage that initially loads the file
		dudeImage = dude.getScaledInstance(dude.getWidth(), dude.getHeight(), Image.SCALE_SMOOTH);
		
		addMouseListener(this);
		
		
		//instantiates timer for refresh purposes - changing 60 to smaller number means slower, choppier movement
		timer = new Timer (1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	shoot(mousex, mousey);
                move();
                repaint();
            }
        });
        timer.start();
		
		addKeyBindings();
    }
	
	public void addKeyBindings() {
		
		//keystrokes for movement
		InputMap im = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "dPressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "dReleased");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "aPressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "aReleased");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "sPressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "sReleased");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "wPressed");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "wReleased");
		
		//add keystroke actions
		ActionMap ap = this.getActionMap();
		//for button d
		ap.put("dPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dPressed = true;
			}
		});
		ap.put("dReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dPressed = false;
			}
		});
		
		//for button w
		ap.put("wPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wPressed = true;
			}
		});
		ap.put("wReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wPressed = false;
			}
		});
		
		//for button a
		ap.put("aPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aPressed = true;
			}
		});
		ap.put("aReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aPressed = false;
			}
		});
		
		//for button s
		ap.put("sPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sPressed = true;
			}
		});
		ap.put("sReleased", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sPressed = false;
			}
		});
	}
	
	public void move() {
		//these if statements tell the image what to do when a certain key is pressed
		if (dPressed) {
			if (me.getX() + me.getSpeed() < getPanelWidth() - dude.getWidth()) {
				me.setX(me.getX() + me.getSpeed());
			}
		}
		if (aPressed) {
			if(me.getX() - me.getSpeed() > 0) {
				me.setX(me.getX() - me.getSpeed());
			}
		}
		if (sPressed) {
			if(me.getY() + me.getSpeed() < this.getHeight() - dude.getHeight()) {
				me.setY(me.getY() + me.getSpeed());
			}
		}
		if (wPressed) {
			if(me.getY() - me.getSpeed() > 0) {
				me.setY(me.getY() - me.getSpeed());
			}
		}
	}
	
	public void shoot(int mousex, int mousey)
	{		
		if (bulletShot)
		{
			bulletObject.setAngle(Math.atan2(mousey - me.getY(), mousex - me.getX()));
			bulletObject.setXVel((bulletV) * Math.cos(bulletObject.getAngle()));
			bulletObject.setYVel((bulletV) * Math.sin(bulletObject.getAngle()));
			bullet.x += bulletObject.getXVel();
			bullet.y += bulletObject.getYVel();
		}
		
		if (otherBulletShot)
		{
			otherBulletObject.setXVel((bulletV) * Math.cos(otherBulletObject.getAngle()));
			otherBulletObject.setYVel((bulletV) * Math.sin(otherBulletObject.getAngle()));
			otherBullet.x += otherBulletObject.getXVel();
			otherBullet.y += otherBulletObject.getYVel();
		} 
	}
	
	public void removeBullet() {
		
		if (bulletShot) {
			if (bullet.x <= 10 || bullet.x >= 720) {
				bullet = new Rectangle(0,0,0,0);
			}
			if (bullet.y <= 10 || bullet.y >= 690) {
				bullet = new Rectangle(0,0,0,0);
			}
			
			if (Math.abs(bullet.x - other.getX()) < 20 && Math.abs(bullet.y - other.getY()) < 15)
			{
				other.updateHealth(-bulletObject.getDamage());
				bullet = new Rectangle(0,0,0,0);
			}
		}
		
		if (otherBulletShot) {
			if (bullet.x <= 10 || bullet.x >= 720) {
				bullet = new Rectangle(0,0,0,0);
			}
			if (bullet.y <= 10 || bullet.y >= 690) {
				bullet = new Rectangle(0,0,0,0);
			}
			
			if (Math.abs(bullet.x - other.getX()) < 20 && Math.abs(bullet.y - other.getY()) < 15)
			{
				other.updateHealth(-bulletObject.getDamage());
				bullet = new Rectangle(0,0,0,0);
			}
		}
		
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		//paints the player image on the panel
        super.paintComponent(g);
        g.drawImage(dudeImage, me.getX(), me.getY(), this);
        
        //draws a bullet if one is shot
        if (bulletShot) {
        	g.setColor(Color.BLACK);
        	g.drawRect(bullet.x,bullet.y,bullet.width,bullet.height);
        }
	}
	
	public int getPanelWidth() {
		return this.getWidth();
	}

	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		bulletShot = true;
		bx = me.getX() + 18;
		by = me.getY() + 15;
		bullet = new Rectangle(bx, by, 3, 5);
		mousex = e.getX();
		mousey = e.getY();
		System.out.println(mousex + "   " + mousey);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}