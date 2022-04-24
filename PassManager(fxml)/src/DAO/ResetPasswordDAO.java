package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GeneralSettings.Settings;
import application.User;

public class ResetPasswordDAO {
	public static User getUser(String username)
	{
		ResultSet result = null;
		User user = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			String isExistSQL = "SELECT count(1) FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
			ResultSet isExist = statement.executeQuery(isExistSQL);
			
			
			if(isExist.getInt(1) == 0)
			{
				connection.close();
				return null;
			}
			
			
			String searchAccountSql = "SELECT * FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
					
			result = statement.executeQuery(searchAccountSql);

			
			// create User bean and return it
			int userID = result.getInt("userID");
			String password = result.getString("userPassword");
			String question = result.getString("secQuestion");
			String answer = result.getString("answer");
			
			user = new User(userID, username, password, question, answer);
			connection.close();
			return user;

		}
		catch (SQLException e) 
		{
			
			System.out.println("Error in getting user name List");
			
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static void updateUserPassword(int userID, String newPassword)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.usernameTable
					+ " SET " 
					+ "userPassword = '" + newPassword + "' " 

					+ " WHERE " 
					+ "userID = '" + userID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in reseting password");
			
			e.printStackTrace();	
		}
	}
 
}
