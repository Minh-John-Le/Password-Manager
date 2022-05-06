package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import GeneralSettings.Settings;
import application.Account;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class UpdateAccountDAO {
	private static PassUtil passUtil = new PassUtil();
	
	/**
	 * This method update use account name in database
	 * @param userID
	 * @param accID
	 * @param newAppName
	 */
	public static void updateAccountAppName(int userID, int accID, String newAppName)
	{
		try 
		{		
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
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
			System.out.println("Error in editing account's application name");
			
			e.printStackTrace();	
		}
	} 
	
	/**
	 * This method update account user name in database
	 * @param userID
	 * @param accID
	 * @param newAccUserName
	 */
	public static void updateAccountUsername(int userID, int accID, String newAccUsername)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "accountUsername = '" + newAccUsername + "' "
				
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accID + "'";
			
			statement.executeUpdate(sql);
			connection.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account username");
			
			e.printStackTrace();	
		}
	} 
	
	/**
	 * This method update account password in database
	 * @param userID
	 * @param accID
	 * @param newPasssword
	 */
	public static void updateAccountPassword(int userID, int accID, String newPassword)
	{
		try 
		{
			String password = passUtil.encrypt(newPassword); // Encrypt new password
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "accountPass = '" + password + "'" 
				
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

	/**
	 * This method update account email in database
	 * @param userID
	 * @param accID
	 * @param newEmail
	 */
	public static void updateAccountEmail(int userID, int accID, String newEmail)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
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

	/**
	 * This method update account creation day in database
	 * @param userID
	 * @param accID
	 * @param newCreationDay
	 */
	public static void updateAccountCreationDay(int userID, int accID, String newCreationDay)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "dateCreated = '" + newCreationDay + "'" 
				
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

	/**
	 * This method update account expired date in database
	 * @param userID
	 * @param accID
	 * @param newExpiredDay
	 */
	public static void updateAccountExpiredDay(int userID, int accID, String newExpiredDay)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + Settings.accountTable
					+ " SET " 
					+ "dateExpire = '" + newExpiredDay + "'" 
				
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

	/**
	 * This method update account duration in database
	 * @param userID
	 * @param accID
	 * @param newDuration
	 */
	public static void updateAccountDuration(int userID, int accID, String newDuration)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
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

	/**
	 * This method get an account based on user ID and accID
	 * @param userID
	 * @param accID
	 * @return
	 */
	public static Account getAccount(int userID, int accID)
	{
		Account account = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			String searchAccountSql = null;
			
			
			searchAccountSql = "SELECT * FROM " + Settings.accountTable
						+ " WHERE " 
						+ "userID = '" + userID + "'"
						+ " AND " 
						+ "accountID = '" + accID + "'";
			
			
			ResultSet result = statement.executeQuery(searchAccountSql);
			
			while (result.next())
			 {
				 
				String applicationName = result.getString("appName");
				String accountName = result.getString("accountUsername");
				String password = result.getString("accountPass");
				password = passUtil.decrypt(password);
				String email = result.getString("email");
				String dateCreated = result.getString("dateCreated");
				String dateExpire = result.getString("dateExpire");				
				String duration = result.getString("duration");
				
				account = new Account(userID, accID, applicationName, accountName, password, email, dateCreated, dateExpire, duration);
				
				//System.out.println(uniqueID + "  |  " + name + "  |  " + password + " | " + question +  "  |  " + answer);
			
			 }
			connection.close();
			return account;
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account by userID, accID");
			
			e.printStackTrace();	
		}
		
		return account;
	}

	/**
	 * This method verified if an account has already exist
	 * @param userID
	 * @param appName
	 * @param accountUsername
	 * @return
	 */
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

}

