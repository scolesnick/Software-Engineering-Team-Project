package client;

import javax.swing.*;
import messageData.GameActionData;
import server.ServerMessage;
import gameMechanics.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;

public class GameControl implements ActionListener, MouseListener
{
	private JPanel container;
	private GameClient client;
	private JLabel status;
	
	
	//GameMap objects
	private Player player;
	private Player opponent;
	private Bullets player_bullets;
	private Bullets opponent_bullets;
	
	//GameMap functionality
	private boolean dPressed, aPressed, sPressed, wPressed;

	public GameControl(JPanel container, GameClient client)
	{
		this.client = client;
		this.container = container;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();

		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();

		if (command == "Logout")
		{
			client.displayLoginPanel();
			gameMap.pauseGame();
		}
		else if (command == "Exit Game")
		{
			client.displayGameMenuPanel();
			gameMap.pauseGame();
		}
	}

	public void updateStatus(String updateStatus)
	{
		GamePanel gamePanel = (GamePanel) container.getComponent(4);
		gamePanel.updateStatus(updateStatus);
	}

	public void displayGamePanel()
	{
		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();
		gameMap.startGame();
		CardLayout cLayout = (CardLayout) container.getLayout();
		cLayout.show(container, "game");
	}

	
	//TODO update positions and send data to server
	public void update() {}
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public Bullets getPlayer_bullets() {
		return player_bullets;
	}

	public void setPlayer_bullets(Bullets player_bullets) {
		this.player_bullets = player_bullets;
	}

	public Bullets getOpponent_bullets() {
		return opponent_bullets;
	}

	public void setOpponent_bullets(Bullets opponent_bullets) {
		this.opponent_bullets = opponent_bullets;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		player_bullets = new Bullets();
		player_bullets.setX(player.getX() + 18);
		player_bullets.setY(player.getY() + 15);
		player_bullets.setBox();
		player_bullets.setFly(true);
		
		player_bullets.setVelocity(e.getX(), e.getY());
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	// reusable method for adding keybinds in a dynamic way
	public void addOneKeyBinding(JComponent comp, int keyCode, boolean bool, String id, ActionListener AL)
	{
		
		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = comp.getActionMap();

		im.put(KeyStroke.getKeyStroke(keyCode, 0, bool), id);

		am.put(id, new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AL.actionPerformed(e);
			}
		});
	}

	// this is where you use addOneKeyBinding to create your keybindings
	public void addAllKeyBindings()
	{
		

		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();
		
		addOneKeyBinding(gameMap, KeyEvent.VK_W, false, "wPressed", (evt) ->
		{
			wPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_W, true, "wReleased", (evt) ->
		{
			wPressed = false;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_A, false, "aPressed", (evt) ->
		{
			aPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_A, true, "aReleased", (evt) ->
		{
			aPressed = false;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_S, false, "sPressed", (evt) ->
		{
			sPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_S, true, "sReleased", (evt) ->
		{
			sPressed = false;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_D, false, "dPressed", (evt) ->
		{
			dPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_D, true, "dReleased", (evt) ->
		{
			dPressed = false;
		});
	}
}
