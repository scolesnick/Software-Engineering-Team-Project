package server;

public enum ServerMessage {

	
	InvalidLogin("Invalid login credentials"),
	ExistingAccount("Username already exists"),
	CreateSuccess("Creation Successful"),
	DatabaseError("Error with Server Database"),
	GameAlreadyInPlay("This game is already in play"),
	GameListUpdate,
	HostGame,
	JoinGameSuccess("Player has joined a game"),
	HostGameSuccess("Player has hosted a game"),
	GameWon("You have won the game!"),
	GameLost("You have lost the game!"),
	StartGame("Game has started!"),
	StopGame("Game has stopped!");
	
	
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
