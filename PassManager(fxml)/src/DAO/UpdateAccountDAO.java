package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import GeneralSettings.Settings;

public class UpdateAccountDAO {
	
	public static void updateAccountAppName(int userID, int accID, String newAppName)
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
	
	public static void updateAccountUsername(int userID, int accID, String newAccUsername)
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

	public static void updateAccountPassword(int userID, int accID, String newPassword)
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

	public static void updateAccountEmail(int userID, int accID, String newEmail)
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

	public static void updateAccountCreationDay(int userID, int accID, String newCreationDay)
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

	public static void updateAccountExpiredDay(int userID, int accID, String newExpiredDay)
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

	public static void updateAccountDuration(int userID, int accID, String newDuration)
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

