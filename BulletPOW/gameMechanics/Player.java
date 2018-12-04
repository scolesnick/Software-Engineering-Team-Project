package gameMechanics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Player{
	
	private int health;
	private int speed;
	private int damage;
	private int x, y;
	private int xbound, ybound;
	

	
	
	public Player(int x, int y, int xbound, int ybound)
	{
		health = 5;
		speed = 5;
		damage = 1;
		this.x = x;
		this.y = y;
		
		this.xbound = xbound;
		this.ybound = ybound;
	}
	

	
	public int getXBound() {return xbound;}
	public int getYBound() {return ybound;}
	
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