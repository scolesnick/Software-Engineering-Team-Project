package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import gameMechanics.*;
import messageData.BulletData;
import messageData.GameActionData;
import ocsf.server.ConnectionToClient;

public class GameInfo {


	private ConnectionToClient hostClient;
	private ConnectionToClient guestClient;

	private Player guest;
	private Player host;
	private ArrayList<Bullet> hostBullets;
	private ArrayList<Bullet> guestBullets;

	private Long guestID;
	private Long hostID;
	private String gameName;
	private Timer timer;

	//Server hosted bounds of the game map
	public static final int xBounds = 720, yBounds = 690;


	public GameInfo(String gameName, ConnectionToClient client) 
	{

		//Host info
		this.hostClient = client;
		this.hostID = hostClient.getId();

		//Guest Info
		guestID = null;
		guestClient = null;

		//Game Info
		this.gameName = gameName;

		//Game Objects
		this.guest = new Player(0, 0, 40, 40);
		this.host = new Player(0,0,40,40);

		this.guestBullets = new ArrayList<>();
		this.hostBullets = new ArrayList<>();



		timer = new Timer(1000/60, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					Iterator<Bullet> it;
					//Host Bullet Updates
					it = hostBullets.iterator();
					while(it.hasNext()) 
					{
						Bullet b = it.next();
						if(moveBullet(b)) 
						{
							//Check Collision for guest players
							if(checkCollision(b, guest)) 
							{

								System.out.println("oof");
								guest.setHealth(guest.getHealth() - b.getDamage());
								it.remove();
								if(guest.getHealth() < 0) 
								{
									guestClient.sendToClient(ServerMessage.GameLost);
									hostClient.sendToClient(ServerMessage.GameWon);
									stopGame();
								}
							}
						}
						else 
						{
							it.remove();
						}
					}


					it = guestBullets.iterator();
					while(it.hasNext()) 
					{
						Bullet b = it.next();
						if(moveBullet(b)) 
						{
							//Check Collision for both players
							if(checkCollision(b, host)) 
							{
								host.setHealth(host.getHealth() - b.getDamage());
								it.remove();
								if(host.getHealth() < 0) 
								{
									guestClient.sendToClient(ServerMessage.GameWon);
									hostClient.sendToClient(ServerMessage.GameLost);
									stopGame();	
								}
							}
						}
						else 
						{
							it.remove();
						}
					}

					BulletData bulletData = new BulletData(hostBullets, guestBullets);
					hostClient.sendToClient(bulletData);
					guestClient.sendToClient(bulletData);
					
					hostClient.sendToClient(new GameActionData(guest));
					guestClient.sendToClient(new GameActionData(host));
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void startGame() {

		if(guestClient != null && hostClient != null)
		{
			timer.start();
		}	
	}

	public void stopGame() {timer.stop();}


	public String getGameName() 
	{
		return gameName;
	}

	public void setGameName(String gameName) 
	{
		this.gameName = gameName;
	}

	public Player getGuest() 
	{
		return guest;
	}

	public void setGuest(Player guest) 
	{
		this.guest = guest;
	}

	public Player getHost() 
	{
		return host;
	}

	public void setHost(Player host) 
	{
		this.host = host;
	}

	public Long getGuestID() 
	{
		return guestID;
	}

	public void setGuestID(Long guestID) 
	{
		this.guestID = guestID;
	}

	public Long getHostID() 
	{
		return hostID;
	}

	public void setHostID(Long hostID) 
	{
		this.hostID = hostID;
	}

	public void setGuest(ConnectionToClient client) {
		this.guestClient = client;
		this.guestID = client.getId();

	}

	public void newBullet(int mouseX, int mouseY, ConnectionToClient client) 
	{
		if(client.equals(hostClient)) 
		{
			Bullet b = new Bullet(host.getX(), host.getY());
			b.setVelocity(mouseY, mouseX);
			hostBullets.add(b);

		}
		else if(client.equals(guestClient)) 
		{
			Bullet b = new Bullet(guest.getX(), guest.getY());
			b.setVelocity(mouseY, mouseX);
			guestBullets.add(b);
		}
	}

	public boolean moveBullet(Bullet b) 
	{
		int nextX = b.nextX(), nextY = b.nextY();

		if(nextX < 0 || nextX > xBounds || nextY < 0 || nextY > yBounds) 
		{
			return false;
		}
		else 
		{
			b.step();
			return true;
		}
	}

	public boolean checkCollision(Bullet b, Player p) 
	{
		int xbound = p.getX() + p.getXBound();
		int ybound = p.getY() + p.getYBound();
		int bulletxbound = b.getX() + 8;
		int bulletybound = b.getY() + 8;
		
		if(b.getX() >= p.getX() && b.getX() <= xbound && b.getY() >= p.getY() && b.getY() <= ybound) {return true;}
		else if(bulletxbound >= p.getX() && bulletxbound <= xbound && bulletybound >= p.getY() && bulletybound <= ybound) {return true;}
		else {return false;}
	}
}