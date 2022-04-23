package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import GeneralSettings.Settings;
import application.User;

public class LoginDAO {
	
	public static User getUser(String username){
		User user = null;
		try {
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			// check for user
			String isExistSQL = "SELECT count(1) FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
			ResultSet isExist = statement.executeQuery(isExistSQL);
			
			
			if(isExist.getInt(1) == 1)
			{
			// search for user
			String searchUser = "SELECT * FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
					
			ResultSet result = statement.executeQuery(searchUser);
				
				
			int userID = result.getInt("userID");
			String password = result.getString("userPassword");
			String question = result.getString("secQuestion");
			String answer = result.getString("answer");
			
			user = new User(userID, username, password, question, answer);
			connection.close();
			}
			else return null;
			// create User bean and return it
			

		}
		catch (SQLException e) 
		{
			System.out.println("Error in getting user name List");
			
			e.printStackTrace();
		}
		return user;
		

	}
}
