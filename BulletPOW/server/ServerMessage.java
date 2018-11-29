package server;

public enum ServerMessage {

	
	InvalidLogin("Invalid login credentials"),
	ExistingAccount("Username already exists"),
	CreateSuccess("Creation Successful"),
	DatabaseError("Error with Server Database");
	
	
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
