package gameMechanics;

public class Buffs 
{
	private int damage;
	private int gainHealth;
	private int speedMult;
	private int duration;
	
	public void setDamage(int d)
	{
		damage = d;
	}
	
	public void setGainHealth(int g)
	{
		gainHealth = g;
	}
	
	public void speedMult(int s)
	{
		speedMult = s;
	}
	
	public void setDuration(int d)
	{
		duration = d;
	}
	
	public int getDamage()
	{
		return damage;
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
}