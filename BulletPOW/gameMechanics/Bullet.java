package gameMechanics;

import java.awt.Rectangle;
import java.io.Serializable;

public class Bullet 
{
	private int damage;
	private int x;
	private int y;
	private double angle, xVel=0, yVel=0;
	private int bullet_speed=10;
	
	public Bullet(int x, int y) 
	{
		damage = 1;
		
		this.x = x;
		this.y = y;
		
	}

	public void setVelocity(int mouseY, int mouseX) 
	{
		angle = Math.atan2(mouseY-y, mouseX-x);
		xVel = bullet_speed * Math.cos(angle);
		yVel = bullet_speed * Math.sin(angle);
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
	
	public int nextX() {return this.x + (int)xVel;}
	public int nextY() {return this.y + (int)yVel;}
	public void step() {this.y += yVel; this.x += xVel;}	

}