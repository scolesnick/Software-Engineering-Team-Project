package messageData;

import java.awt.Event;
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

}
