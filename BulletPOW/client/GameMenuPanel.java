package client;

import javax.swing.*;
import java.awt.*;

public class GameMenuPanel extends JPanel
{
	private JButton hostGame;
	private JButton joinGame;
	private JButton logOut;
	
	// Constructor
	public GameMenuPanel(GameMenuControl gmc)
	{
		// buffer panel
		JPanel buffer = new JPanel();
		
		// menu panel
		JPanel menuPanel = new JPanel(new GridLayout(3, 1, 15, 15));
		
		// create buttons
		hostGame = new JButton("Host Game");
		joinGame = new JButton("Join Game");
		logOut = new JButton("Logout");
		hostGame.addActionListener(gmc);
		joinGame.addActionListener(gmc);
		logOut.addActionListener(gmc);
		
		// add buttons to the menu panel
		menuPanel.add(hostGame);
		menuPanel.add(joinGame);
		menuPanel.add(logOut);
		
		//grid panel 
		JPanel grid = new JPanel(new GridLayout(2, 1));
		grid.add(buffer);
		grid.add(menuPanel);
		this.add(grid);
	}

}