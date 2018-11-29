package messageData;

import java.io.Serializable;

public class CreateAccountData implements Serializable{
	
	private String username;
	private String password;
	private String verifyPassword;
	
	public String getUsername() {
		
		return username;
		
	}
	
	public String getPassword() {
		
		return password;
		
	}
	
	public String getVerifyPassword() {
		
		return verifyPassword;
		
	}
	
	public void setUsername(String username) {
		
		this.username = username;
		
	}
	
	public void setPassword(String password) {
		
		this.password = password;
		
	}
	
	public void setVerifyPassword(String verifyPassword) {
		
		this.verifyPassword = verifyPassword;
		
	}
	
	public CreateAccountData(String username, String password, String verifyPassword) {
		
		setUsername(username);
		setPassword(password);
		setVerifyPassword(verifyPassword);
		
	}

}
