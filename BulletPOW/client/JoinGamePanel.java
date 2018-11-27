package client;

import javax.swing.*;
import java.awt.*;

public class JoinGamePanel extends JPanel
{
	private JList gameLists;
	private JButton refresh;
	private JButton join;
	private JButton back;
	private String games = "Fake Game1\nFake Game2\nFake Game3";
	
	// Constructor
	public JoinGamePanel(JoinGameControl jgc)
	{
		// buffer panel
		JPanel buffer = new JPanel();
		
		// panel for game list
		JPanel listPanel = new JPanel(new GridLayout(1,1,5,5));
	    JTextArea contactList = new JTextArea(games);
	    contactList.setEditable(false);
	    listPanel.add(contactList);
	    
	    // panel for buttons
	    JPanel buttons = new JPanel(new GridLayout(1, 3, 5, 5));
	    join = new JButton("Join");
	    refresh = new JButton("Refresh");
	    back = new JButton("Back");
	    join.addActionListener(jgc);
	    refresh.addActionListener(jgc);
	    back.addActionListener(jgc);
	    buttons.add(join);
	    buttons.add(refresh);
	    buttons.add(back);
	    
	    // grid
	    JPanel grid = new JPanel(new GridLayout(3, 1));
	    grid.add(buffer);
	    grid.add(listPanel);
	    grid.add(buttons);
	    this.add(grid);
	    
	}

}
