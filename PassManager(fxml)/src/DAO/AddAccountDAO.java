package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import GeneralSettings.Settings;

public class AddAccountDAO {
	
	public static boolean isAccountExist(int userID, String appName, String accountUsername){
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
					
					+ "appName = '" + appName + "'"
					
					+ " AND " 
					
					+ "accountUsername = '" + accountUsername + "'";
			
			result = statement.executeQuery(sql);
			
			if (result.getInt(1) == 1)
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
	
	public static boolean addNewAccount(int userID, String appName, String accountUsername,
			String accountPass, String email, String dateCreated, String dateExpire, String duration) 
	{
		try 
		{
			
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
	        addAccountPstmt.setString(5, accountPass);
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
