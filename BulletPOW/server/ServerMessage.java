package server;

public enum ServerMessage {

	
	InvalidLogin("Invalid login credentials"),
	ExistingAccount("Username already exists"),
	CreateSuccess("Creation Successful"),
	DatabaseError("Error with Server Database"),
	GameListUpdate("do not know what to put here");
	
	
	private String message;
	
	ServerMessage(String message) 
	{
		this.message = message;
	}
	
	public String getMessage() 
	{
		return message;
	}
	
}
