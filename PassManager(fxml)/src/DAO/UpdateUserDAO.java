package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import GeneralSettings.Settings;

public class UpdateUserDAO {
	
	public static void updateUserPassword(String userID, String newPassword)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.usernameTable
					+ " SET " 
					+ "userPassword = '" + newPassword + "'"

					+ " WHERE " 
					+ "userID = '" + userID + "'";
			
			statement.executeUpdate(sql);

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing user password");
			
			e.printStackTrace();	
		}
	}
	
	public static void UpdateUserSecQuestion(String userID, String newQuestion)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.usernameTable
					+ " SET " 
					+ "secQuestion = '" + newQuestion + "'"

					+ " WHERE " 
					+ "userID = '" + userID + "'";
			
			statement.executeUpdate(sql);

		}
		catch (SQLException e) 
		{
			System.out.println("Error in update user Question");
			
			e.printStackTrace();	
		}
	
	}
	
	
	public static void UpdateUserAnswer(String userID, String newAnswer)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.usernameTable
					+ " SET " 
					+ "answer = '" + newAnswer + "'"

					+ " WHERE " 
					+ "userID = '" + userID + "'";
			
			statement.executeUpdate(sql);

		}
		catch (SQLException e) 
		{
			System.out.println("Error in update user answer");
			
			e.printStackTrace();	
		}
	
	}
}
