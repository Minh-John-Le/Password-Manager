package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import GeneralSettings.Settings;

public class UpdateAccountDAO {
	
	public static void updateAccountAppName(String userID, String accID, String newAppName)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "appName = '" + newAppName + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 
	
	public static void updateAccountUsername(String userID, String accID, String newAccUsername)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "accountUsername = '" + newAccUsername + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 

	public static void updateAccountPassword(String userID, String accID, String newPassword)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "accountPass = '" + newPassword + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 

	public static void updateAccountEmail(String userID, String accID, String newEmail)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "email = '" + newEmail + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 

	public static void updateAccountCreationDay(String userID, String accID, String newCreationDay)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "creationDay = '" + newCreationDay + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 

	public static void updateAccountExpiredDay(String userID, String accID, String newExpiredDay)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "expiredDay = '" + newExpiredDay + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 

	public static void updateAccountDuration(String userID, String accID, String newDuration)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.accountTable);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "duration = '" + newDuration + "'" 
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	} 

}

