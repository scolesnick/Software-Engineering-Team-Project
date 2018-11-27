package client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreateAccountControl implements ActionListener
{
	private JPanel container;
	private GameClient client;
	private JLabel status;
	
	public CreateAccountControl(JPanel container, GameClient gc)
	{
		this.container = container;
		this.client = gc;
	}
	
	public void Validate()
	{
		
	}
	public void displayError(String error)
	{
		CreateAccountPanel createPanel = (CreateAccountPanel)container.getComponent(2);
	    createPanel.setError(error);
	}
	public void displayLoginPanel()
	{
		CardLayout cLayout = (CardLayout)container.getLayout();
		cLayout.show(container, "1");
	}

	public void createSuccess()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String command = e.getActionCommand();
		
		if(command == "Back")
		{
			displayLoginPanel();
		}
		
	}

}
