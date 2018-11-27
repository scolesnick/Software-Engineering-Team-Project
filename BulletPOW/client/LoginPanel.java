package client;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel
{
	private JButton submit;
//	private JButton back; no back button needed, first panel?
	private JButton create;
	private JLabel errorLabel;
	private JTextField username;
	private JTextField password;

	public JTextField getUsername()
	{
		return username;
	}
	public JTextField getPassword()
	{
		return password;
	}
	public void setError(String error)
	{
		errorLabel.setText(error);
	}	  
	
	public LoginPanel(LoginControl lc)
	{
		// Create a panel for the labels at the top of the GUI.
	    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
	    errorLabel = new JLabel("", JLabel.CENTER);
	    errorLabel.setForeground(Color.RED);
	    JLabel instructionLabel = new JLabel("Enter your username and password to log in.", JLabel.CENTER);
	    labelPanel.add(errorLabel);
	    labelPanel.add(instructionLabel);
	    
	    // create panel for login form
	    JPanel loginPanel = new JPanel(new GridLayout(2, 2, 5, 5));
	    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
	    username = new JTextField(10);
	    JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
	    password = new JPasswordField(10);
	    loginPanel.add(usernameLabel);
	    loginPanel.add(username);
	    loginPanel.add(passwordLabel);
	    loginPanel.add(password);
	    
	    // create a panel for buttons
	    JPanel buttonPanel = new JPanel();
	    submit = new JButton("Submit");
	    create = new JButton("Create");
	    submit.addActionListener(lc);
	    create.addActionListener(lc);
	    buttonPanel.add(submit);
	    buttonPanel.add(create);
	    
	    // arrange panels in a grid
	    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
	    grid.add(labelPanel);
	    grid.add(loginPanel);
	    grid.add(buttonPanel);
	    this.add(grid);
	    
	}

}
