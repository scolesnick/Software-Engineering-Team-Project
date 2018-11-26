package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database
{
  private Connection conn;
  Properties prop = new Properties();
  FileInputStream inp;
  String user;
  String password;
  String url;
  Statement stmt;
  ResultSet rs;
  ResultSetMetaData rmd;
  ArrayList<String> ResultSet = new ArrayList<String>();
  
  public Database()
  {
	  
	  try {
		  inp = new FileInputStream("db.properties");
		  prop.load(inp);
		  
		  user = prop.getProperty("user");
		  password = prop.getProperty("password");
		  url = prop.getProperty("url");
		  
		  conn = DriverManager.getConnection(url, user, password);
		  
		} catch (IOException e1) {} catch (SQLException e) {e.printStackTrace();}
	  
  }
  
  public ArrayList<String> query(String query)
  {
	  try {
		  
		  stmt=conn.createStatement();
		  rs=stmt.executeQuery(query);
		  rmd = rs.getMetaData();
		  
		  while(rs.next()) {
			  
			  ResultSet.add(rs.getString(1));
			  ResultSet.add(rs.getString(2));
			  
		  }
		  
	  } catch (SQLException e) {e.printStackTrace();}
	  
	  return ResultSet;
	  
  }
  
  public void executeDML(String dml) throws SQLException {
	  
      stmt=conn.createStatement();
      stmt.execute(dml);
      
  }
  
}