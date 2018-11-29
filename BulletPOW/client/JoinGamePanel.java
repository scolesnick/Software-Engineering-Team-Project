package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;


public class JoinGamePanel extends JPanel{

	  public JoinGamePanel(JoinGameControl jgc) 
	  {        
		    //North Panel
		    JPanel north = new JPanel();
		    JLabel contactLabel = new JLabel("Games", JLabel.CENTER);
		    north.add(contactLabel);
		    
		    //Center Contacts Panel
		    //Caleb: JList data needs to be generated from the server, possibly done at 'Join Game' event in GameMenuController
		    JPanel center = new JPanel();
		    String[] data = {"Game 1", "Game 2", "Game 3", "Game 4"}; 	
		    JList<String> contactArea = new JList<String>(data);
		    center.add(contactArea);
		    
		    //South Buttons Panel
		    JPanel south = new JPanel(new GridLayout(2, 1, 5, 5));
		    
		    JPanel contactsButtonPanel = new JPanel();
		    JButton join = new JButton("Join");
		    JButton refresh = new JButton("Refresh");
		    contactsButtonPanel.add(join);
		    contactsButtonPanel.add(refresh);
		    
		    JPanel logoutButtonPanel = new JPanel();
		    JButton back = new JButton("Back");
		    logoutButtonPanel.add(back);
		    
		    join.addActionListener(jgc);
		    refresh.addActionListener(jgc);
		    back.addActionListener(jgc);
		    
		    south.add(contactsButtonPanel);
		    south.add(logoutButtonPanel);
		    
		    
		    
		    this.setLayout(new BorderLayout());
		    this.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		    this.add(north, BorderLayout.NORTH);
		    this.add(contactArea, BorderLayout.CENTER);
		    this.add(south, BorderLayout.SOUTH);
		    
	  }
}
