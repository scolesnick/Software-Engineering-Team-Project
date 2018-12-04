package gameMechanics;

import java.awt.Rectangle;

public class Buffs 
{
	private int damageMult = 1;
	private int gainHealth = 0;
	private int speedMult = 1;
	private int duration;
	private int buffType;
	private Double dBuffType;
	
	private Rectangle box = new Rectangle(0,0,0,0);
	private Double dx, dy;
	private int x, y;
	
	private Player player;
	
	public void setDamageMult(int d)
	{
		damageMult = d;
	}
	
	public void setGainHealth(int g)
	{
		gainHealth = g;
	}
	
	public void setSpeedMult(int s)
	{
		speedMult = s;
	}
	
	public void setDuration(int d)
	{
		duration = d;
	}
	
	public int getDamageMult()
	{
		return damageMult;
	}
	
	public int getGainHealth()
	{
		return gainHealth;
	}
	
	public int getSpeedMult()
	{
		return speedMult;
	}
	
	public int getDuration()
	{
		return duration;
	}
	
	public int getBuffType()
	{
		return buffType;
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
	public void setY()
	{
		this.y = y;
	}
	public void setType(int type)
	{
		this.buffType = type;
	}
	public Rectangle getBox()
	{
		return box;
	}
	
	public void createBuff()
	{
		dx = Math.random() * 710 + 10;
		dy = Math.random() * 680 + 10;
		x = dx.intValue();
		y = dy.intValue();
		box = new Rectangle(x, y, 15, 15);
		
		dBuffType = Math.random() * 2;
		buffType = dBuffType.intValue();
		
		if(buffType == 0)
		{
			this.setDuration(5);
			this.setGainHealth(5);
		}
		
		else if(buffType == 1)
		{
			this.setDuration(5);
			this.setSpeedMult(2);
		}
		
		else
		{
			this.setDuration(5);
			this.setDamageMult(2);
		}
	}
	
	public void useBuff(Player p)
	{
		player = p;
		
		if(buffType == 0)
		{
			player.updateHealth(this.getGainHealth());
		}
		
		else if(buffType == 1)
		{
			player.setSpeed(this.getSpeedMult() * player.getSpeed());
		}
		
		else
		{
			player.setDamage(player.getDamage() * this.getDamageMult());
		}
		
		box = new Rectangle(0,0,0,0);
	}
	
	public void removeBuff(Player p)
	{
		player = p;
		
		/*if(buffType == 0)
		{
			player.updateHealth(this.getGainHealth());
		}*/
		
		if(buffType == 1)
		{
			player.setSpeed(player.getSpeed() / this.getSpeedMult());
		}
		
		else
		{
			player.setDamage(player.getDamage() / this.getDamageMult());
		}
	}
	
	public Buffs() {}
	
}