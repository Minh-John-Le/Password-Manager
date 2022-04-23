package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import GeneralSettings.Settings;
import application.Account;

public class SearchAccountDAO {
	
	
	public static ArrayList<Account> getAllAccount(int userID)
	{
		ResultSet result = null;
		ArrayList<Account> accountList = new ArrayList<Account>();
		try 
		{			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + Settings.accountTable
					+ " WHERE " 
					+ "userID = '" + userID + "'";
			result = statement.executeQuery(searchAccountSql);
			
			while (result.next())
			 {
				 
				int accID = result.getInt("accountID");
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
			connection.close();
			return accountList;

		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account");
			
			e.printStackTrace();	
		}
		
		return  null;
	}

	
	public static ArrayList<Account> getAccount(int userID, String appName, String accountUsername)
	{
		ArrayList<Account> accountList = new ArrayList<Account>();
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			String searchAccountSql = null;
			
			
			if(accountUsername != null && appName == null){
				searchAccountSql = "SELECT * FROM " + Settings.accountTable
						+ " WHERE " 
						+ "userID = '" + userID + "'"
						+ " AND " 
						+ "accountUsername LIKE '%" + accountUsername + "%';";
			}
			else if(appName != null && accountUsername == null ) {
				searchAccountSql = "SELECT * FROM " + Settings.accountTable
						+ " WHERE " 
						+ "userID = '" + userID + "'"
						+ " AND " 
						+ "appName LIKE '%" + appName + "%';";
			}
			else {
				 searchAccountSql = "SELECT * FROM " + Settings.accountTable
							+ " WHERE " 
							+ "userID = '" + userID + "'"
							+ " AND " 
							+ "(appName LIKE '%" + appName + "%'"
							+ " AND " 
							+ "accountUsername LIKE '%" + accountUsername + "%');";
					
				}
			
			
			ResultSet result = statement.executeQuery(searchAccountSql);
			
			while (result.next())
			 {
				 
				int accID = result.getInt("accountID");
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
			connection.close();
			return accountList;
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account by userID, appName and accountUserName");
			
			e.printStackTrace();	
		}
		
		return  null;
	}

}
