package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GeneralSettings.Settings;
import application.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class ResetPasswordDAO {
	private static PassUtil passUtil = new PassUtil();
	
	/**
	 * This method get user based on user name
	 * @param username
	 * @return
	 */
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
			password = passUtil.decrypt(password); // Decypt the password
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
	
	/**
	 * This method update user password in database
	 * @param userID
	 * @param newPassword
	 */
	public static void updateUserPassword(int userID, String newPassword)
	{
		try 
		{
			String encryptedPass = passUtil.encrypt(newPassword); // Encrypt new password
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.usernameTable
					+ " SET " 
					+ "userPassword = '" + encryptedPass + "' " 

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
