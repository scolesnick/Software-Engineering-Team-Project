package testpkg;

import static org.junit.Assert.*;
import server.*;

import java.io.IOException;

import client.*;

import org.junit.Test;

public class GameClientGameGUITest {

	@Test
	public void GameGUIConstructTest()
	{
		GameGUI g = new GameGUI("localhost", 8300);
	}
	
	@Test
	public void GameClientConstructTest() throws IOException
	{
		GameClient g = new GameClient("localhost", 8300);
	}

	@Test
	public void HandleMessageTest() throws IOException
	{
		GameClient g = new GameClient("localhost", 8300);
		g.handleMessageFromServer("google");
	}
}
