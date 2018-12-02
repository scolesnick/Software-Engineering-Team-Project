package gameMechanics;

import java.io.Serializable;

public class Bullets implements Serializable
{
	
	private int damage = 1;
	private int x;
	private int y;
	private double angle, xVel, yVel;
	
	public void setAngle(double a)
	{
		angle = a;
	}
	
	public void setXVel(double xV)
	{
		xVel = xV;
	}
	
	public void setYVel(double yV)
	{
		yVel = yV;
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
		x += xVel;
		y += yVel;
	}
	
	public Bullets(double angle, double xVel, double yVel, int x, int y, int damage)
	{
		setAngle(angle);
		setXVel(xVel);
		setYVel(yVel);
		setX(x);
		setY(y);
		setDamage(damage);
	}

}
