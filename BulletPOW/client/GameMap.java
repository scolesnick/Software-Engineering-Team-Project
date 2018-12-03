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

public class GameMap extends JPanel
{

	
	Player me;
	Player opponent;
	Bullets my_bullet;
	Bullets opponent_bullet;
	
	GameControl gc;


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

	// mouse click stuff
	private int mousex, mousey;

	// bullet stuff

	public GameMap(GameControl gc)
	{

		this.gc = gc;
		
		setBackground(Color.BLACK);

		// instantiate players/bullets
		me = gc.getPlayer();
		opponent = gc.getOpponent();
		my_bullet = gc.getPlayer_bullets();
		opponent_bullet = gc.getOpponent_bullets();
	

		addMouseListener(gc);
		
		// instantiates timer for refresh purposes - changing 60 to smaller number means
		// slower, choppier movement
		timer = new Timer(1000/60, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		// paints the player image on the panel
		super.paintComponent(g);

		//TODO paint game info
		gc.paintObjects(g);

	}


}