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
	
	
	//objects
	private Player me;	
	private Player opponent;	
	private Bullets my_bullet;
	private Bullets opponent_bullet;
	
	public void setPlayer(Player player) {this.me = player;}
	public void setBullet(Bullets playerBullet) {this.my_bullet = playerBullet;}
	public void setOpponent(Player opponent) {this.opponent = opponent;}
	public void setOpponentBullet(Bullets opponent_bullet) {this.opponent_bullet = opponent_bullet;}

	//timer creation
	private Timer timer;
	public void startGame() {timer.start();}
	public void pauseGame() {timer.stop();}
	
	//keybinding flags
	private boolean t = true, f = false;
	private boolean dPressed, aPressed, sPressed, wPressed;
	private boolean bulletShot = false, otherBulletShot = false;
	
	//mouse click stuff
	private int mousex, mousey;
	
	//bullet stuff

	public GameMap(GameControl gc) {
		
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
            	
            	//Send info to server
            	gc.update(me, my_bullet);

            	
            	
            	shoot(mousex, mousey);
            	removeBullet();
                move();
                my_bullet.moveBullet();
                opponent_bullet.moveBullet();
                repaint();
            }
        });
		
		addAllKeyBindings();
    }
	
	//reusable method for adding keybinds in a dynamic way
	public void addOneKeyBinding(JComponent comp, int keyCode, boolean bool, String id, ActionListener AL) {
		
		InputMap im = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = this.getActionMap();
		
		im.put(KeyStroke.getKeyStroke(keyCode, 0, bool), id);
		
		am.put(id, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AL.actionPerformed(e);
			}
		});
	}
	
	//this is where you use addOneKeyBinding to create your keybindings
	public void addAllKeyBindings() {
		
		addOneKeyBinding(this, KeyEvent.VK_W, f, "wPressed", (evt) -> {
			wPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_W, t, "wReleased", (evt) -> {
			wPressed = false;
		});
		addOneKeyBinding(this, KeyEvent.VK_A, f, "aPressed", (evt) -> {
			aPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_A, t, "aReleased", (evt) -> {
			aPressed = false;
		});
		addOneKeyBinding(this, KeyEvent.VK_S, f, "sPressed", (evt) -> {
			sPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_S, t, "sReleased", (evt) -> {
			sPressed = false;
		});
		addOneKeyBinding(this, KeyEvent.VK_D, f, "dPressed", (evt) -> {
			dPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_D, t, "dReleased", (evt) -> {
			dPressed = false;
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
	
	//is called upon in the timer to constantly be checking and updating the x,y coords of bullets
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
	
	//if a bullet collides with another object or the edges of the panel, it is removed here
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
	
	//this is called in the timer by repaint() to constantly be repainting the panel when a change occurs
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

	//whenever you click your mouse, this is what begins the bullet shooting rabbit hole of methods
	@Override
	public void mouseClicked(MouseEvent e) {
		
		bulletShot = true;
		my_bullet = new Bullets();
		my_bullet.setX(me.getX() + 18);
		my_bullet.setY(me.getY() + 15);
		my_bullet.setBox();
		
		mousex = e.getX();
		mousey = e.getY();
		
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