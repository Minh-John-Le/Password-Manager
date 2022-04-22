package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import GeneralSettings.Settings;
import application.Account;

public class SearchApplicationDAO {
	
	private static ArrayList<Account> getAccountList(ResultSet result)
	{
		ArrayList<Account> accountList = new ArrayList<Account>();
		try 
		{
			while (result.next())
			 {
				 
			
				String userID = result.getString("userID");
				String accID = result.getString("accountID");
				String applicationName = result.getString("appName");
				String accountName = result.getString("accountUsername");
				String password = result.getString("accountPass");
				String email = result.getString("email");
				String dateCreated = result.getString("dateCreated");
				String dateExpire = result.getString("dateExpire");				
				String duration = result.getString("duration");
				
				Account account = new Account(userID, accID,applicationName, accountName, password, email, dateCreated, dateExpire, duration);
				
				accountList.add(account);
				//System.out.println(uniqueID + "  |  " + name + "  |  " + password + " | " + question +  "  |  " + answer);
			
			 }
		}
		catch (SQLException e) 
		{
			System.out.println("Error in getting account list");
			
			e.printStackTrace();
		}
		return accountList;
	}

	
	public static ArrayList<Account> getAllAccount(String userID)
	{
		ResultSet result = null;
		try 
		{			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + Settings.accountTable
					+ " WHERE " 
					+ "userID = '" + userID + "'";
			result = statement.executeQuery(searchAccountSql);
			
			connection.close();
			getAccountList(result);
			
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account");
			
			e.printStackTrace();	
		}
		
		return  null;
	}

	
	public static ArrayList<Account> getAccount(String userID, String appName, String accountUsername)
	{
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + Settings.accountTable
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					+ " AND " 
					
					+ "(appName = '" + appName + "'" + " COLLATE NOCASE "
					+ " OR " 
					+ "accountUsername = '" + accountUsername + "'" + " COLLATE NOCASE)";
				
			
			ResultSet result = statement.executeQuery(searchAccountSql);
			connection.close();
			return getAccountList(result);
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account by userID, appName and accountUserName");
			
			e.printStackTrace();	
		}
		
		return  null;
	}

}
