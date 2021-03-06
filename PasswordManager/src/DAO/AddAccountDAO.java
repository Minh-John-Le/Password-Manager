package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import GeneralSettings.Settings;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class AddAccountDAO {
	private static PassUtil passUtil = new PassUtil();
	/**
	 * This method verified if an account already exist
	 * @param userID
	 * @param appName
	 * @param accountUsername
	 * @return
	 */
	private static boolean isAccountExist(int userID, String appName, String accountUsername){
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			
			// Find if account already exist
			String sql = "SELECT count(1) FROM " + Settings.accountTable
					+ " WHERE " 
					
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "appName = '" + appName + "'" + " COLLATE NOCASE "
					
					+ " AND " 
					
					+ "accountUsername = '" + accountUsername + "'";
			
			result = statement.executeQuery(sql);
			
			if (result.getInt(1) > 0)
			{
				connection.close();
				return true;
			}
			connection.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Error in checking if account exists");
			
			e.printStackTrace();	
		}
		
		return false;
		
	}
	
	/**
	 * This method add new account to database
	 * @param userID
	 * @param appName
	 * @param accountUsername
	 * @param accountPass
	 * @param email
	 * @param dateCreated
	 * @param dateExpire
	 * @param duration
	 * @return
	 */
	public static boolean addNewAccount(int userID, String appName, String accountUsername,
			String accountPass, String email, String dateCreated, String dateExpire, String duration) 
	{
		try 
		{
			// Encrypting input password
			String encryptedPass = passUtil.encrypt(accountPass);
			
			// Connect to database
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement(); 		


			if (isAccountExist(userID, appName, accountUsername))
			{
				connection.close();
				return false;
			}
			
				
		
			String addAccountsql = "INSERT INTO " + Settings.accountTable + 
					"(userID, accountID, appName, accountUsername, accountPass, "
					+ "email, dateCreated, dateExpire, duration) " + "\n" + 
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			
			// add data to table
	        PreparedStatement addAccountPstmt = connection.prepareStatement(addAccountsql);
	        
	        addAccountPstmt.setInt(1, userID);
	        addAccountPstmt.setString(3, appName);
	        addAccountPstmt.setString(4, accountUsername);
	        addAccountPstmt.setString(5, encryptedPass);
	        addAccountPstmt.setString(6, email);
	        addAccountPstmt.setString(7, dateCreated);
	        addAccountPstmt.setString(8, dateExpire);
	        addAccountPstmt.setString(9, duration);
	        
	        addAccountPstmt.executeUpdate();
	        connection.close();
	        
	        return true;

		}
		catch (SQLException e) 
		{
			System.out.println("Error in creating new Account");
			
			e.printStackTrace();	
		}	
		
		return false;
	}
}