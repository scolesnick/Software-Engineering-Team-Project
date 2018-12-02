package gameMechanics;

public class GameInfo {

	private Player guest;
	private Player host;
	private Long guestID;
	private Long hostID;
	private String gameName;
	private Bullets guestBullets;
	private Bullets hostBullets;
	
	public GameInfo(String gameName, Long hostID) 
	{
		this.hostID = hostID;
		this.gameName = gameName;

		guest = new Player();
		host = new Player();
		guestBullets = new Bullets();
		hostBullets = new Bullets();

		guestID = null;
	}

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
}