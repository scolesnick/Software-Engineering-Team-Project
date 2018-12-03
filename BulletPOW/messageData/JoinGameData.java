package messageData;

import java.io.Serializable;
import java.util.ArrayList;
import server.ServerMessage;

public class JoinGameData implements Serializable
{
	String gameName;
	ArrayList<String> gameList;
	
	public JoinGameData(String gameName) 
	{
		this.gameName = gameName;
		this.gameList = null;
	}
	
	public JoinGameData(ArrayList<String> gameList) 
	{
		this.gameList = gameList;
		this.gameName = null;
	}

	public ArrayList<String> getGameList() 
	{
		return gameList;
	}

	public void setGameList(ArrayList<String> gameList) 
	{
		this.gameList = gameList;
	}

	public String getGameName() 
	{
		return gameName;
	}

	public void setGameName(String gameName) 
	{
		this.gameName = gameName;
	}
}