package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import gameMechanics.*;
import messageData.GameActionData;
import ocsf.server.ConnectionToClient;

public class GameInfo {

	
	private ConnectionToClient hostClient;
	private ConnectionToClient guestClient;
	
	private Player guest;
	private Player host;
	private Long guestID;
	private Long hostID;
	private String gameName;
	private Bullets guestBullets;
	private Bullets hostBullets;
	private Timer timer;
	
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
		this.guest = new Player(0, 0, 0, 0);
		this.host = new Player(0,0,0,0);

		
		
		timer = new Timer(1000/70, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hostClient.sendToClient(new GameActionData(guest, guestBullets));
					guestClient.sendToClient(new GameActionData(host, hostBullets));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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

	public Bullets getGuestBullets() 
	{
		return guestBullets;
	}

	public void setGuestBullets(Bullets guestBullets) 
	{
		this.guestBullets = guestBullets;
	}

	public Bullets getHostBullets() 
	{
		return hostBullets;
	}

	public void setHostBullets(Bullets hostBullets) 
	{
		this.hostBullets = hostBullets;
	}
	
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
}