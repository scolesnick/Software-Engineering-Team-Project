package messageData;

import java.io.Serializable;

import gameMechanics.*;

public class GameActionData implements Serializable{
	
	private Bullets bullet;
	private int px, py;
	
	public GameActionData(Player player, Bullets bullet) 
	{
		this.bullet = bullet;
		
		
		px = player.getX();
		py = player.getY();
	}

	public int getPx() {
		return px;
	}

	public void setPx(int px) {
		this.px = px;
	}

	public int getPy() {
		return py;
	}

	public void setPy(int py) {
		this.py = py;
	}
 }