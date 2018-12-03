package messageData;

import java.io.Serializable;

import gameMechanics.*;

public class GameActionData implements Serializable{
	
	private Player player;
	private Bullets bullet;
	private int px;
	private int py;
	
	public GameActionData(Player player, Bullets bullet) 
	{
		this.player = player;
		this.bullet = bullet;
		
		
		px = player.getX();
		py = player.getY();
	}

	public Player getPlayer() 
	{
		return player;
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	public Bullets getBullet() 
	{
		return bullet;
	}

	public void setBullet(Bullets bullet) 
	{
		this.bullet = bullet;
	}
}