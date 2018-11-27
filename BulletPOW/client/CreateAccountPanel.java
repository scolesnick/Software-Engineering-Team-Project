package client;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;

public class CreateAccountPanel extends JPanel
{
	private JButton submit;
	private JButton back;
	private JLabel status;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField password2;
	
	public String getUsername()
	{
		return username.getText();
	}

	public String getPassword()
	{
		return new String(password.getPassword());
	}

	public String getPassword2()
	{
		return new String(password2.getPassword());
	}

	public void setError(String error)
	{
		status.setText(error);
	}
	
	public CreateAccountPanel(CreateAccountControl cac)
	{
		// Create a panel for the labels at the top of the GUI.
	    JPanel labelPanel = new JPanel(new GridLayout(3, 1, 5, 5));
	    status = new JLabel("", JLabel.CENTER);
	    status.setForeground(Color.RED);
	    JLabel instructionLabel = new JLabel("Enter a username and password to create an account.", JLabel.CENTER);
	    JLabel instructionLabel2 = new JLabel("Your Password must be at least 6 characters.", JLabel.CENTER);
	    labelPanel.add(status);
	    labelPanel.add(instructionLabel);
	    labelPanel.add(instructionLabel2);
	    
	    // Create a panel for the login information form.
	    JPanel createPanel = new JPanel(new GridLayout(3, 2, 5, 5));
	    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
	    username = new JTextField(10);
	    JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
	    password = new JPasswordField(10);
	    JLabel passwordLabel2 = new JLabel("Verify Password:", JLabel.RIGHT);
	    password2 = new JPasswordField(10);
	    createPanel.add(usernameLabel);
	    createPanel.add(username);
	    createPanel.add(passwordLabel);
	    createPanel.add(password);
	    createPanel.add(passwordLabel2);
	    createPanel.add(password2);
	    
	    // Create a panel for the buttons.
	    JPanel buttonPanel = new JPanel();
	    submit = new JButton("Submit");
	    submit.addActionListener(cac);
	    back = new JButton("Back");
	    back.addActionListener(cac);    
	    buttonPanel.add(submit);
	    buttonPanel.add(back);
	    
	    // Arrange the three panels in a grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
	    grid.add(labelPanel);
	    grid.add(createPanel);
	    grid.add(buttonPanel);
	    this.add(grid);
	}
}
