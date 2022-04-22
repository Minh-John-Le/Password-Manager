package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import GeneralSettings.Settings;

public class SignUpDAO {
	private static boolean isUserExist(String username)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
	


			// Find if account already exist
			String findUserSql = "SELECT * FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";

			
			result = statement.executeQuery(findUserSql);
			
			if (result.next())
			{
				connection.close();
				return true;
			}
			connection.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Error in checking if user exists");
			
			e.printStackTrace();	
		}
		
		return false;
	}
	
	public static boolean createNewUser(String username, String userPassword, String secQuestion, String answer)
	{
		try 
		{
			
			if (isUserExist(username))
			{
				
				return false; 
			}
			
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			
	        // adding new user name into table
	        String createNewUserSql = "INSERT INTO " + Settings.usernameTable 
	        		+"(userID, username, userPassword, secQuestion, answer) " + "\n" 
	        		+"VALUES(?, ?, ?, ?, ?)";
			
			// add data to table
	        PreparedStatement createNewUserPs = connection.prepareStatement(createNewUserSql);
	        
	        //createNewUserPs.setString(1, userID);
	        createNewUserPs.setString(2, username);
	        createNewUserPs.setString(3, userPassword);
	        createNewUserPs.setString(4, secQuestion); 
	        createNewUserPs.setString(5, answer); 
	        createNewUserPs.executeUpdate();
	        
	        connection.close();
	        return true;
	        
		}
		catch (SQLException e) 
		{
			System.out.println("Error in creating new Users");
			
			e.printStackTrace();	
		}
		return false;
	}
}
