package messageData;

import java.io.Serializable;

public class MouseClickData implements Serializable{

	private int mouseX, mouseY;

	public MouseClickData(int mouseX, int mouseY) 
	{
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
}
