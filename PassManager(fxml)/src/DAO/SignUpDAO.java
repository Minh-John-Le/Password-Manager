package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import GeneralSettings.Settings;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class SignUpDAO {

	private static PassUtil passUtil = new PassUtil();
	/**
	 * This method add new user into database
	 * @param username
	 * @param userPassword
	 * @param secQuestion
	 * @param answer
	 * @return
	 */
	public static boolean createNewUser(String username, String userPassword, String secQuestion, String answer)
	{
		try 
		{
			String encryptedPass = passUtil.encrypt(userPassword); // Encypt user password
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			// Check if user name already exist
			String isExistSQL = "SELECT count(1) FROM " + Settings.usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
			ResultSet isExist = statement.executeQuery(isExistSQL);
			
			
			if(isExist.getInt(1) > 0)
			{
				connection.close();
				return false;
			}
			
					
	        // adding new user name into table
	        String createNewUserSql = "INSERT INTO " + Settings.usernameTable 
	        		+"(userID, username, userPassword, secQuestion, answer) " + "\n" 
	        		+"VALUES(?, ?, ?, ?, ?)";
			
			// add data to table
	        PreparedStatement createNewUserPs = connection.prepareStatement(createNewUserSql);
	        
	        //createNewUserPs.setString(1, userID);
	        createNewUserPs.setString(2, username);
	        createNewUserPs.setString(3, encryptedPass);
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
