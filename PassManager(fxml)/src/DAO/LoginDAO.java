package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import GeneralSettings.Settings;
import application.Account;
import application.User;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class LoginDAO {
	private static PassUtil passUtil = new PassUtil();
	/**
	 * This method get user from database based on user name
	 * @param username
	 * @return searched user
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
			password = passUtil.decrypt(password);
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
	 * This method return all search account based on Application name and account User name
	 * This method follow "And" logic
	 * @param userID
	 * @param appName
	 * @param accountUsername
	 * @return
	 */
	public static ArrayList<Account> getAccount(int userID, String appName, String accountUsername)
	{
		ArrayList<Account> accountList = new ArrayList<Account>();
		try 
		{
			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			String searchAccountSql = null;
			
			
			if(!accountUsername.equals("") && appName.equals(""))
			{
				searchAccountSql = "SELECT * FROM " + Settings.accountTable
						+ " WHERE " 
						+ "userID = '" + userID + "'"
						+ " AND " 
						+ "accountUsername LIKE '%" + accountUsername + "%';";
			}
			else if(!appName.equals("") && accountUsername.equals("")) 
			{
				searchAccountSql = "SELECT * FROM " + Settings.accountTable
						+ " WHERE " 
						+ "userID = '" + userID + "'"
						+ " AND " 
						+ "appName LIKE '%" + appName + "%';";
			}
			else 
			{
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
				password = passUtil.decrypt(password);
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
		
		return accountList;
	}


}
