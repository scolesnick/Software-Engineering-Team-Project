package gameMechanics;

import java.awt.Rectangle;
import java.io.Serializable;

public class Bullets implements Serializable
{
	private int damage = 1;
	private int x;
	private int y;
	private double angle, xVel=0, yVel=0;
	
	private Rectangle box = new Rectangle(0,0,0,0);
	private int bullet_speed=10;
	private int b_width = 8, b_height = 8;
	
	public void setBox() 
	{
		box = new Rectangle(x,y,b_width,b_height);
	}
	public void resetBox() 
	{
		box = new Rectangle(0,0,0,0);
		x = y = 0;
		xVel = yVel = 0;
	}
	public Rectangle getBox() 
	{
		return box;
	}
	
	public void setVelocity(int mouseY, int mouseX) 
	{
		angle = Math.atan2(mouseY-y, mouseX-x);
		xVel = bullet_speed * Math.cos(angle);
		yVel = bullet_speed * Math.sin(angle);
//		System.out.println("angle: "+angle+"\txVel: "+xVel+"\tyVel"+yVel);
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setDamage(int d)
	{
		damage = d;
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	public double getXVel()
	{
		return xVel;
	}
	
	public double getYVel()
	{
		return yVel;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void moveBullet()
	{
		box.x += xVel;
		box.y += yVel;
	}
	
	public Bullets() {};
}