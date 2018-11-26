package client;

import javax.swing.JPanel;

public class LoginControl
{
	private JPanel container;
	private GameClient client;
	
	public LoginControl(GameClient gc, JPanel container)
	{
		this.container = container;
		this.client = gc;
	}

}
