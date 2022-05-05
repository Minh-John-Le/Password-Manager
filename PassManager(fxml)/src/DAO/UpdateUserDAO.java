package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GeneralSettings.Settings;
import application.Account;
import application.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class UpdateUserDAO {
	private static PassUtil passUtil = new PassUtil();
	public static User getUser(int userID)
	{
		User user = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			String searchAccountSql = null;
			
			
			searchAccountSql = "SELECT * FROM " + Settings.usernameTable
						+ " WHERE " 
						+ "userID = '" + userID + "'";
			
			
			// create User bean and return it
			ResultSet result = statement.executeQuery(searchAccountSql);
			
			String username = result.getString("username");
			String password = result.getString("userPassword");
			password = passUtil.decrypt(password);
			String question = result.getString("secQuestion");
			String answer = result.getString("answer");
			
			user = new User(userID, username, password, question, answer);
			connection.close();
			return user;

		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching user by userID");
			
			e.printStackTrace();	
		}
		
		return user;
	}
	
	public static void UpdateUserSecQuestion(int userID, String newQuestion)
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
	
	
	public static void UpdateUserAnswer(int userID, String newAnswer)
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
