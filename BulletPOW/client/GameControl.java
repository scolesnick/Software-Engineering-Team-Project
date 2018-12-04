package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import messageData.GameActionData;
import messageData.MouseClickData;
import server.ServerMessage;
import gameMechanics.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameControl implements ActionListener, MouseListener {
	private JPanel container;
	private GameClient client;
	private JLabel status;

	// Graphic Components
	private Image playerImage;
	private Image opponentImage;

	// GameMap objects
	private Player player;
	private Player opponent;
	private Buffs buff;
	int[][] hostBullets;
	int[][] guestBullets;
	GameMap gameMap;

	// GameMap functionality
	private boolean dPressed, aPressed, sPressed, wPressed;

	public GameControl(JPanel container, GameClient client) {
		hostBullets = new int[0][2];
		guestBullets = new int[0][2];
		this.client = client;
		this.container = container;

	}
	
	public void setBuff(int x, int y, int type)
	{
		buff = new Buffs();
		buff.setX(x);
		buff.setY();
		buff.setType(type);
	}

	public void initialize(ServerMessage msg) {
		
		hostBullets = new int[0][2];
		guestBullets = new int[0][2];
		try {
			BufferedImage image = ImageIO.read(new File("steve.jpg"));
			BufferedImage image2 = ImageIO.read(new File("creeper.jpg"));

			gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();
			switch (msg) {
			case JoinGameSuccess:
				opponentImage = image.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
				playerImage = image2.getScaledInstance(image2.getWidth(), image2.getHeight(), Image.SCALE_SMOOTH);
				player = new Player(400, 300, image2.getWidth(), image2.getHeight());
				opponent = new Player(-100, -100, image.getWidth(), image.getHeight());
				break;
			case HostGameSuccess:
				playerImage = image.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
				opponentImage = image2.getScaledInstance(image2.getWidth(), image2.getHeight(), Image.SCALE_SMOOTH);
				opponent = new Player(-100, -100, image2.getWidth(), image2.getHeight());
				player = new Player(100, 300, image.getWidth(), image.getHeight());
				break;
			default:
				break;
			}
			

			addAllKeyBindings();
			
			gameMap.startGame();
		} catch (IOException e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();

		if (command == "Logout") {
			client.displayLoginPanel();
			gameMap.pauseGame();
		} else if (command == "Exit Game") {
			client.displayGameMenuPanel();
			gameMap.pauseGame();
		}
	}

	public void updateStatus(String updateStatus) {
		GamePanel gamePanel = (GamePanel) container.getComponent(4);
		gamePanel.updateStatus(updateStatus);
	}

	public void displayGamePanel() {
		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();
		gameMap.startGame();
		CardLayout cLayout = (CardLayout) container.getLayout();
		cLayout.show(container, "game");
	}

	// TODO update positions and send data to server - bullets
	public void update() {
		move();
		try {
			GameActionData data = new GameActionData(player);
			client.sendToServer(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintObjects(Graphics g) {
		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();
		// Draw the player images
		if (player != null)
			g.drawImage(playerImage, player.getX(), player.getY(), gameMap);
		if (opponent != null)
			g.drawImage(opponentImage, opponent.getX(), opponent.getY(), gameMap);

		// Draw buff if exists
		g.setColor(Color.ORANGE);
		if (buff != null)
		{
			Rectangle a_buff = new Rectangle(buff.getX(), buff.getY(), 15, 15);
			g.drawRect(buff.getX(), buff.getY(), 15, 15);
			g.fillRect(buff.getX(), buff.getY(), 15, 15);
		}
		
		//Draw all the bullets
		g.setColor(Color.RED);
		for(int i = 0; i < hostBullets.length; i++) 
		{
			Rectangle bullet = new Rectangle(hostBullets[i][0], hostBullets[i][1], 8, 8);

			g.drawRect(bullet.x, bullet.y, bullet.width, bullet.height);
			g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
		}
		
		g.setColor(Color.CYAN);
		for(int i = 0; i < guestBullets.length; i++) 
		{
			Rectangle bullet = new Rectangle(guestBullets[i][0], guestBullets[i][1], 8, 8);

			g.drawRect(bullet.x, bullet.y, bullet.width, bullet.height);
			g.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
		}
	}

	public void move() {
		// these if statements tell the image what to do when a certain key is pressed
		if (dPressed) {
			if (player.getX() + player.getSpeed() < gameMap.getWidth() - player.getXBound()) {
				player.setX(player.getX() + player.getSpeed());
			}
		}
		if (aPressed) {
			if (player.getX() - player.getSpeed() > 0) {
				player.setX(player.getX() - player.getSpeed());
			}
		}
		if (sPressed) {
			if (player.getY() + player.getSpeed() < gameMap.getHeight() - player.getYBound()) {
				player.setY(player.getY() + player.getSpeed());
			}
		}
		if (wPressed) {
			if (player.getY() - player.getSpeed() > 0) {
				player.setY(player.getY() - player.getSpeed());
			}
		}
	}
	
	public void setHostBullets(int[][] bullets) 
	{
		hostBullets = bullets;
	}
	
	public void setGuestBullets(int[][] bullets) 
	{
		guestBullets = bullets;
	}

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

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			client.sendToServer(new MouseClickData(e.getX(), e.getY()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	// reusable method for adding keybinds in a dynamic way
	public void addOneKeyBinding(JComponent comp, int keyCode, boolean bool, String id, ActionListener AL) {

		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = comp.getActionMap();

		im.put(KeyStroke.getKeyStroke(keyCode, 0, bool), id);

		am.put(id, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AL.actionPerformed(e);
			}
		});
	}

	// this is where you use addOneKeyBinding to create your keybindings
	public void addAllKeyBindings() {

		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();

		addOneKeyBinding(gameMap, KeyEvent.VK_W, false, "wPressed", (evt) -> {
			wPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_W, true, "wReleased", (evt) -> {
			wPressed = false;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_A, false, "aPressed", (evt) -> {
			aPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_A, true, "aReleased", (evt) -> {
			aPressed = false;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_S, false, "sPressed", (evt) -> {
			sPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_S, true, "sReleased", (evt) -> {
			sPressed = false;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_D, false, "dPressed", (evt) -> {
			dPressed = true;
		});
		addOneKeyBinding(gameMap, KeyEvent.VK_D, true, "dReleased", (evt) -> {
			dPressed = false;
		});
	}
}
