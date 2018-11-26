package messageData;

public class CreateAccountData {
	
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
	
	//josh - i did this validation in a differenct class
	public void UsernameAlreadyInUse() {
		
		
		
	}
	
	//josh - i did this validation in a differenct class
	public boolean IncorrectPassword() {
		
		
		//simply for error purposes - this return true statement will need to be removed/displaced/replaced
		return true;
	}

}
