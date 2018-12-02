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

import gameMechanics.*;

public class GameMap extends JPanel implements ActionListener,MouseListener{
	
	//dude image stuff
	private BufferedImage dude;
	private Image dudeImage;
	private int x=0, y=0, player_speed=5;
	
	
	//objects
	private Player me;	
	private Player opponent;	
	private Bullets my_bullet;
	private Bullets opponent_bullet;

	//timer creation
	private Timer timer;
	
	//keybinding flags
	private boolean dPressed, aPressed, sPressed, wPressed, spPressed, mouseClicked = false;
	private boolean bulletShot = false, otherBulletShot = false;
	
	//mouse click stuff
	private int mousex, mousey;
	
	//bullet stuff

	public GameMap() {
		
		//creates a buffered image from the jpg stored above the package
		try {dude = ImageIO.read(new File("guy1.jpg"));} catch (IOException ex) {}
		
		setBackground(Color.BLACK);
		
		// instantiate players/bullets
		me = new Player();
		opponent = new Player();
		my_bullet = new Bullets();
		opponent_bullet = new Bullets();
		
		//creates an Image based on the BufferedImage that initially loads the file
		dudeImage = dude.getScaledInstance(dude.getWidth(), dude.getHeight(), Image.SCALE_SMOOTH);
		
		addMouseListener(this);
		
		
		//instantiates timer for refresh purposes - changing 60 to smaller number means slower, choppier movement
		timer = new Timer (1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	shoot(mousex, mousey);
            	removeBullet();
                move();
                my_bullet.moveBullet();
                opponent_bullet.moveBullet();
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
			my_bullet.setVelocity(mousey, mousex);
			my_bullet.moveBullet();
		}
		
		if (otherBulletShot)
		{
			opponent_bullet.setVelocity(mousey, mousex);
			opponent_bullet.moveBullet();
		} 
	}
	
	public void removeBullet() {
		
		if (bulletShot) {
			if (my_bullet.getX() <= 10 || my_bullet.getX() >= 720) {
				my_bullet.resetBox();
			}
			if (my_bullet.getY() <= 10 || my_bullet.getY() >= 690) {
				my_bullet.resetBox();
			}
			
			if (Math.abs(my_bullet.getX() - opponent.getX()) < 20 && Math.abs(my_bullet.getY() - opponent.getY()) < 15)
			{
				opponent.updateHealth(-my_bullet.getDamage());
				my_bullet.resetBox();
			}
		}
		
		if (otherBulletShot) {
			if (opponent_bullet.getX() <= 10 || opponent_bullet.getX() >= 720) {
				opponent_bullet.resetBox();
			}
			if (opponent_bullet.getY() <= 10 || opponent_bullet.getY() >= 690) {
				opponent_bullet.resetBox();
			}
			
			if (Math.abs(opponent_bullet.getX() - me.getX()) < 20 && Math.abs(opponent_bullet.getY() - me.getY()) < 15)
			{
				me.updateHealth(-opponent_bullet.getDamage());
				opponent_bullet.resetBox();
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
        	Rectangle bullet = my_bullet.getBox();
        	g.setColor(Color.YELLOW);
        	g.drawRect(bullet.x,bullet.y,bullet.width,bullet.height);
        	g.fillRect(bullet.x,bullet.y,bullet.width,bullet.height);
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
		my_bullet = new Bullets();
		my_bullet.setX(me.getX() + 18);
		my_bullet.setY(me.getY() + 15);
		my_bullet.setBox();
		
		mousex = e.getX();
		mousey = e.getY();
//		System.out.println(mousex + "   " + mousey);
		
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