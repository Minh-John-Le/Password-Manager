package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import GeneralSettings.Settings;
import application.User;

public class LoginDAO {
	
	public static User getUser(String username)
	{
		ResultSet result = null;
		User user = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			// Search for user
			String searchAccountSql = "SELECT * FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
					
			result = statement.executeQuery(searchAccountSql);
			
			if(result == null)
			{
				return null;
			}
			
			
			// create User bean and return it
			int userID = result.getInt("userID");
			String password = result.getString("userPassword");
			String question = result.getString("secQuestion");
			String answer = result.getString("answer");
			
			user = new User(userID, username, password, question, answer, null);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in getting user name List");
			
			e.printStackTrace();
		}
		
		return user;

	}
}
