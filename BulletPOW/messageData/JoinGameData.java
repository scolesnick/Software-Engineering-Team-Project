package messageData;

import java.io.Serializable;
import java.util.ArrayList;
import gameMechanics.GameInfo;
import server.ServerMessage;

public class JoinGameData implements Serializable
{
	String gameName;
	GameActionData gameData;
	ServerMessage messageType;
	ArrayList<String> gameList;
	
	public JoinGameData(String gameName) 
	{
		messageType = null;
		this.gameName = gameName;
		this.gameData = null;
		this.gameList = null;
	}
	
	public JoinGameData(GameActionData gameData) 
	{
		messageType = ServerMessage.JoinGame;
		this.gameData = gameData;
		this.gameList = null;
		this.gameName = null;
	}
	
	public JoinGameData(ArrayList<GameInfo> gameList) 
	{
		this.messageType = ServerMessage.GameListUpdate;
		this.gameList = new ArrayList<>();
		
		for(GameInfo game : gameList) 
		{
			this.gameList.add(game.getGameName());
		}
		
		this.gameData = null;
		this.gameName = null;
	}

	public ServerMessage getMessageType() 
	{
		return messageType;
	}

	public void setMessageType(ServerMessage messageType) 
	{
		this.messageType = messageType;
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

	public GameActionData getGameData() 
	{
		return gameData;
	}

	public void setGameData(GameActionData gameData) 
	{
		this.gameData = gameData;
	}
}