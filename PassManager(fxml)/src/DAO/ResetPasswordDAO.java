package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GeneralSettings.Settings;
import application.User;

public class ResetPasswordDAO {
	private static ResultSet searchUser(String username)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
					
			result = statement.executeQuery(searchAccountSql);
			
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching username");
			
			e.printStackTrace();	
		}
		
		return  result;
	}
	
	
	private static User getUser(ResultSet result)
	{
		User user = null;
		
		try
		{
			int userID = result.getInt("userID");
			String username = result.getString("username");
			String password = result.getString("userPassword");
			String question = result.getString("secQuestion");
			String answer = result.getString("answer");
			
			//System.out.println(uniqueID + "  |  " + name + "  |  " + password + " | " + question +  "  |  " + answer);
			user = new User(userID, username, password, question, answer);
				
		}
		catch (SQLException e) 
		{
			System.out.println("Error in getting user name List");
			
			e.printStackTrace();
		}
		return user;
	}
	
	public static User gettingUser(String username)
	{
		ResultSet result = searchUser(username);
		
		if(result == null)
		{
			return null;
		}
		
		return getUser(result); 
		
	}

	public static void updateUserPassword(String username, String newPassword)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.usernameTable
					+ " SET " 
					+ "userPassword = '" + newPassword + "' , " 

					+ " WHERE " 
					+ "username = '" + username + "'";
			
			statement.executeUpdate(sql);

		}
		catch (SQLException e) 
		{
			System.out.println("Error in reseting password");
			
			e.printStackTrace();	
		}
	}
 
}
