package messageData;

import java.io.Serializable;
import java.util.ArrayList;

import gameMechanics.*;

public class GameActionData implements Serializable{
	
	private int px, py;
	
	public GameActionData(Player player) 
	{
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