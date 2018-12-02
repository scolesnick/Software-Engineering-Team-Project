package gameMechanics;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
	
	private messageData.LoginData data;
	private int health = 5;
	private int speed = 5;
	private int x = 0, y = 0;
	//private ArrayList<Bullets> bullets;

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
	
	/*public ArrayList<Bullets> getBullets()
	{
		return bullets;
	}*/
	
	/*public void addBullet(Bullets b)
	{
		bullets.add(b);
	}*/
	
	public void updateHealth(int h)
	{
		this.health += h;
	}
	
	public Player()
	{
		
	}
}
