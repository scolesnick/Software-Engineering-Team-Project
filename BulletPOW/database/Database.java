package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import messageData.*;
import server.ServerMessage;

public class Database
{
  private Connection conn;

  private String key = "key";
  
  public Database()
  {
	  try {
		  FileInputStream inp = new FileInputStream("database/db.properties");
		  Properties prop = new Properties();
		  prop.load(inp);
		  
		  String user = prop.getProperty("user");
		  String password = prop.getProperty("password");
		  String url = prop.getProperty("url");
		  
		  conn = DriverManager.getConnection(url, user, password);
		  
		} catch (IOException e1) {} catch (SQLException e) {e.printStackTrace();}
  }
  
	public User getUser(LoginData data) 
	{
		ArrayList<String> rows;
		
		String statement = String.format("select username, cast(aes_decrypt(password, '%s') as char(40)) from users where username='%s' and password=aes_encrypt('%s', '%s')", this.key, data.getUsername(), data.getPassword(), this.key);

		rows = this.query(statement);
		if(rows.size() < 1) 
		{
			return null;
		}
		else 
		{
			String tokens[] = rows.get(0).split(",");
			return new User(tokens[0], tokens[1]);
		}
	}
	
	public ServerMessage createAccount(CreateAccountData data) 
	{
		//insert into uuser values (username, aes_encrypt(password, key))
		String statement = String.format("insert into users values ('%s', aes_encrypt('%s', '%s'))", data.getUsername(), data.getPassword(), this.key);
		
		try
		{
			this.executeDML(statement);
			return ServerMessage.CreateSuccess;
		} catch (SQLException e)
		{
			if(e.getMessage().contains("Duplicate"))
				return ServerMessage.ExistingAccount;
			else
				return ServerMessage.DatabaseError;
		}
	}
  
  public ArrayList<String> query(String query)
  {
	  ArrayList<String> resultSet = new ArrayList<>();
	  try {
		  Statement stmt = conn.createStatement();
		  ResultSet rs = stmt.executeQuery(query);
		  ResultSetMetaData rmd = rs.getMetaData();
		  
		  while(rs.next()) 
		  {
		    	//make empty string to append to
		        String row = "";
		        
		        //look to each column and append field as a string to temporary string
		        for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) 
		        {
		        	row = row + rs.getString(i) + ","; 
		        }
		        
		        if(row.length() > 0)
		        	row = row.substring(0, row.length()); //trim off last comma
		        
		        resultSet.add(row); //add to returnable arraylist
		  }
		  
	  } catch (SQLException e) {e.printStackTrace();}
	  
	  return resultSet;
  }

  public void executeDML(String dml) throws SQLException 
  {
      Statement stmt = conn.createStatement();
      stmt.execute(dml);
  }
}