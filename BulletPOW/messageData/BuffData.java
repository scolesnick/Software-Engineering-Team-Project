package messageData;

import java.io.Serializable;

public class BuffData implements Serializable
{
	private int x;
	private int y;
	private int type;
	
	public BuffData(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX(){return x;}
	public int getY() {return y;}
	public int getType() {return type;}

}
