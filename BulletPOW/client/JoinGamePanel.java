package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

public class JoinGamePanel extends JPanel
{
	private JList<String> gameArea;
	private DefaultListModel<String> listModel;

	public void updateGameList(ArrayList<String> gameList)
	{
		for (String gameName : gameList)
		{
			listModel.addElement(gameName);
		}
	}

	public String getSelectedValue()
	{
		return gameArea.getSelectedValue();
	}

	public JoinGamePanel(JoinGameControl jgc)
	{
		listModel = new DefaultListModel<String>();
		// listModel.addElement("item 1");

		// listModel.addElement("item 2");
		// North Panel
		JPanel north = new JPanel();
		JLabel contactLabel = new JLabel("Games", JLabel.CENTER);
		north.add(contactLabel);

		// Center Game Panel
		// Caleb: JList data needs to be generated from the server, possibly done at
		// 'Join Game' event in GameMenuController
		JPanel center = new JPanel();

		gameArea = new JList(listModel);
		center.add(gameArea);

		// South Buttons Panel
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
		this.add(gameArea, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
	}
}