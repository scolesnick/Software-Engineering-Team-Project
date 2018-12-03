package gameMechanics;

import java.awt.Rectangle;
import java.io.Serializable;

public class Bullet 
{
	private int damage;
	private int x;
	private int y;
	private double angle, xVel=0, yVel=0;
	
//	private Rectangle box;
	private int bullet_speed=10;
	
	public Bullet(int x, int y) 
	{
		damage = 1;
		
	}

//	public void setFly(boolean f)
//	{
//		flying = f;
//	}
//	public boolean getFly()
//	{
//		return flying;
//	}
//	public void setBox() 
//	{
//		box = new Rectangle(x,y,b_width,b_height);
//	}
//	public void resetBox() 
//	{
//		box = new Rectangle(0,0,0,0);
//		System.out.println("box reset");
//		box = null;
//		x = y = 0;
//		xVel = yVel = 0;
//		flying = false;
//	}
//	public Rectangle getBox() 
//	{
//		return box;
//	}
	
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
	
//	private void checkBounds(int xMin, int yMin, int xMax, int yMax)
//	{
//		if (box.x <= xMin || box.x >= xMax)
//		{
//			resetBox();
//		}
//		else if (box.y <= yMin || box.x >= yMax)
//		{
//			resetBox();
//		}
//	}
//	public int checkCollision(int opponentX, int opponentY)
//	{
//		if(box != null)
//			if (Math.abs(box.x - opponentX) < 20 && Math.abs(box.y - opponentY) < 15)
//			{
//				System.out.println("collision");
//				resetBox();
//				return -damage;
//			}
//		return 0;
//	}
	

}