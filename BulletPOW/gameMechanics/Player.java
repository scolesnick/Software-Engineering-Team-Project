package gameMechanics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	
	private int health;
	private int speed;
	private int damage;
	private int x, y;
	private int xbound, ybound;
	
	private Image graphicImage;
	private BufferedImage graphic;

	
	
	public Player(BufferedImage graphic, int x, int y)
	{
		health = 5;
		speed = 1;
		damage = 1;
		this.x = x;
		this.y = y;
		
		xbound = graphic.getWidth();
		ybound = graphic.getHeight();
		
		graphicImage = graphic.getScaledInstance(graphic.getWidth(), graphic.getHeight(), Image.SCALE_SMOOTH);
	}
	
	//TODO Graphics
	public Image getGraphic() 
	{
		return graphicImage;
	}
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int h)
	{
		health = h;
	}
	
	public void setSpeed(int s)
	{
		speed = s;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setDamage(int d)
	{
		damage = d;
	}
	
	public void updateHealth(int h)
	{
		this.health += h;
	}
	
	
	
	
	
}