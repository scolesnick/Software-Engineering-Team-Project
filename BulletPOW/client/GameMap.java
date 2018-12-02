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
import javax.swing.*;
import gameMechanics.*;

public class GameMap extends JPanel implements ActionListener, MouseListener
{
	// dude image stuff
	private BufferedImage dude;
	private Image dudeImage;
	private BufferedImage dude2;
	private Image dudeImage2;

	// objects
	//TODO condense more functionality in GameMap into Player and Bullet object
	private Player me;
	private Player opponent;
	private Bullets my_bullet;
	private Bullets opponent_bullet;
	
	private Buffs aBuff;
	private boolean buffActive = false;
	long timeSinceBuff, timeLastBuff = 0, time;

	public void setPlayer(Player player)
	{
		this.me = player;
	}

	public void setBullet(Bullets playerBullet)
	{
		this.my_bullet = playerBullet;
	}

	public void setOpponent(Player opponent)
	{
		this.opponent = opponent;
	}

	public void setOpponentBullet(Bullets opponent_bullet)
	{
		this.opponent_bullet = opponent_bullet;
	}

	// timer creation
	private Timer timer;

	public void startGame()
	{
		timer.start();
	}

	public void pauseGame()
	{
		timer.stop();
	}

	// keybinding flags
	private boolean t = true, f = false;
	private boolean dPressed, aPressed, sPressed, wPressed;

	// mouse click stuff
	private int mousex, mousey;

	// bullet stuff

	public GameMap(GameControl gc)
	{
		// creates a buffered image from the jpg stored above the package
		try
		{
			dude = ImageIO.read(new File("guy1.jpg"));
			dude2 = ImageIO.read(new File("guy2.jpg"));
		} catch (IOException ex){}

		setBackground(Color.BLACK);

		// instantiate players/bullets
		me = new Player();
		opponent = new Player();
		my_bullet = new Bullets();
		opponent_bullet = new Bullets();
		aBuff = new Buffs();
		

		// creates an Image based on the BufferedImage that initially loads the file
		dudeImage = dude.getScaledInstance(dude.getWidth(), dude.getHeight(), Image.SCALE_SMOOTH);
		dudeImage2 = dude2.getScaledInstance(dude2.getWidth(), dude2.getHeight(), Image.SCALE_SMOOTH);

		addMouseListener(this);
		
		// instantiates timer for refresh purposes - changing 60 to smaller number means
		// slower, choppier movement
		timer = new Timer(1000 / 60, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				move();
//				System.out.println("Bullet flying: "+my_bullet.getFly());
				if(my_bullet.getFly()) {
					System.out.println("bullet is flying");
					my_bullet.moveBullet();
				}
				me.updateHealth(opponent_bullet.checkCollision(me.getX(), me.getY()));

				// Send info to server
				time = System.nanoTime();
				timeSinceBuff = (time - timeLastBuff) * 1000000000;
				
				if(timeSinceBuff > 5)
				{
					aBuff.createBuff();
					buffActive = true;
				}
				// gc update commented for bullet glitch
//				gc.update(me, my_bullet);
				move();
				useBuff();
				repaint();
			}
		});
		
		addAllKeyBindings();
	}

	// reusable method for adding keybinds in a dynamic way
	public void addOneKeyBinding(JComponent comp, int keyCode, boolean bool, String id, ActionListener AL)
	{
		InputMap im = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = this.getActionMap();

		im.put(KeyStroke.getKeyStroke(keyCode, 0, bool), id);

		am.put(id, new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AL.actionPerformed(e);
			}
		});
	}

	// this is where you use addOneKeyBinding to create your keybindings
	public void addAllKeyBindings()
	{
		addOneKeyBinding(this, KeyEvent.VK_W, f, "wPressed", (evt) ->
		{
			wPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_W, t, "wReleased", (evt) ->
		{
			wPressed = false;
		});
		addOneKeyBinding(this, KeyEvent.VK_A, f, "aPressed", (evt) ->
		{
			aPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_A, t, "aReleased", (evt) ->
		{
			aPressed = false;
		});
		addOneKeyBinding(this, KeyEvent.VK_S, f, "sPressed", (evt) ->
		{
			sPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_S, t, "sReleased", (evt) ->
		{
			sPressed = false;
		});
		addOneKeyBinding(this, KeyEvent.VK_D, f, "dPressed", (evt) ->
		{
			dPressed = true;
		});
		addOneKeyBinding(this, KeyEvent.VK_D, t, "dReleased", (evt) ->
		{
			dPressed = false;
		});
	}

	public void move()
	{
		// these if statements tell the image what to do when a certain key is pressed
		if (dPressed)
		{
			if (me.getX() + me.getSpeed() < getPanelWidth() - dude.getWidth())
			{
				me.setX(me.getX() + me.getSpeed());
			}
		}
		if (aPressed)
		{
			if (me.getX() - me.getSpeed() > 0)
			{
				me.setX(me.getX() - me.getSpeed());
			}
		}
		if (sPressed)
		{
			if (me.getY() + me.getSpeed() < this.getHeight() - dude.getHeight())
			{
				me.setY(me.getY() + me.getSpeed());
			}
		}
		if (wPressed)
		{
			if (me.getY() - me.getSpeed() > 0)
			{
				me.setY(me.getY() - me.getSpeed());
			}
		}
	}	
	public void useBuff()
	{
		if(buffActive)
		{
			if (Math.abs(aBuff.getX() - me.getX()) < 20 && Math.abs(aBuff.getY() - me.getY()) < 15)
			{
				aBuff.useBuff(me);
			}
			
			/*if (Math.abs(aBuff.getX() - opponent.getX()) < 20 && Math.abs(aBuff.getY() - opponent.getY()) < 15)
			{
				aBuff.useBuff(opponent);
			}*/
		}
	}

	// this is called in the timer by repaint() to constantly be repainting the
	// panel when a change occurs
	@Override
	protected void paintComponent(Graphics g)
	{
		// paints the player image on the panel
		super.paintComponent(g);
		g.drawImage(dudeImage, me.getX(), me.getY(), this);
		g.drawImage(dudeImage2, opponent.getX(), opponent.getY(), this);

		// draws a bullet if one is shot
		if (my_bullet.getFly())
		{
			Rectangle bullet = my_bullet.getBox();
			System.out.println("drawing rectangle: "+bullet);
			g.setColor(Color.YELLOW);
			g.drawRect(bullet.x, bullet.y, bullet.width, bullet.height);
			g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
		}
		
		if (buffActive)
		{
			Rectangle buff = aBuff.getBox();
			g.setColor(Color.RED);
			g.drawRect(buff.x, buff.y, buff.width, buff.height);
			g.fillRect(buff.x, buff.y, buff.width, buff.height);
		}
	}

	public int getPanelWidth()
	{
		return this.getWidth();
	}

	// whenever you click your mouse, this is what begins the bullet shooting rabbit
	// hole of methods
	@Override
	public void mousePressed(MouseEvent e) 
	{
		System.out.println("mouse pressed");
		my_bullet = new Bullets();
		my_bullet.setX(me.getX() + 18);
		my_bullet.setY(me.getY() + 15);
		my_bullet.setBox();
		my_bullet.setFly(true);
		
		mousex = e.getX();
		mousey = e.getY();
		my_bullet.setVelocity(mousey, mousex);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0){}
}