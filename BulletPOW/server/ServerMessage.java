package server;

public enum ServerMessage {

	
	InvalidLogin("Invalid login credentials"),
	ExistingAccount("Username already exists"),
	CreateSuccess("Creation Successful"),
	DatabaseError("Error with Server Database"),
	GameAlreadyInPlay("This game is already in play"),
	GameListUpdate,
	GameUpdate,
	HostGame,
	JoinGame;
	
	
	private String message;
	
	ServerMessage(String message) 
	{
		this.message = message;
	}
	
	private ServerMessage() 
	{
		this.message = this.name();
	}
	
	public String getMessage() 
	{
		return message;
	}
	
}
