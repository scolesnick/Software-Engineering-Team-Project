package testpkg;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;

import client.*;

public class GameMenuTest {
	
	
	GameClient client;
	private JPanel container;
	private GameMenuControl gmc;
	private GameMenuPanel menuPanel;
	private JPanel joinPanel;
	private JPanel gamePanel;
	private JPanel loginPanel;
	
	@Before
	public void setUp() 
	{
		client = new GameClient();
		
		CardLayout cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		
		gmc = new GameMenuControl(container, client);
		menuPanel = new GameMenuPanel(gmc);
		
		joinPanel = new JPanel();
		gamePanel = new JPanel();
		loginPanel = new JPanel();
		
		container.add(loginPanel, "login");
		container.add(gamePanel, "game");
		container.add(joinPanel, "join");
		container.add(menuPanel, "menu");
	}
	
	@Test(expected = ConnectException.class)
	public void testCreateClient() throws IOException 
	{
		GameClient client = new GameClient("localhost", 8080);
	}
	
	public void testDisplayGameMenuPanel() 
	{
		gmc.displayGameMenuPanel();
	}

}
