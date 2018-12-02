package client;

import javax.swing.*;

import messageData.GameActionData;
import server.ServerMessage;
import gameMechanics.*;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;

public class GameControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	private JLabel status;

	public GameControl(JPanel container, GameClient client)
	{
		this.client = client;
		this.container = container;
	}

	/*
	 * GameMap mechanics updated here
	 */
	public void updateEnemy(GameActionData gameData)
	{
		GameMap gameMap = (GameMap) ((GamePanel) container.getComponent(4)).getGameMap();

		gameMap.setOpponent(gameData.getPlayer());
		gameMap.setBullet(gameData.getBullet());
	}

	public void update(Player myPlayer, Bullets myBullets)
	{
		try
		{
			client.sendToServer(ServerMessage.GameUpdate);
			client.sendToServer(new GameActionData(myPlayer, myBullets));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void applyBuff()
	{

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
		} else if (command == "Exit Game")
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

}
