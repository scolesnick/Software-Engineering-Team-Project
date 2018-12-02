package messageData;

import java.io.Serializable;

import gameMechanics.*;

public class GameActionData implements Serializable{
	
	private GameInfo game;
	private Player player;
	private Bullets bullet;
	
	public GameActionData(Player player, Bullets bullet) 
	{
		this.player = player;
		this.bullet = bullet;
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