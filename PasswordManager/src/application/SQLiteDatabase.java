
package application;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.omg.CORBA.Environment;

public class SQLiteDatabase {
	private static String jdbcUrl = "jdbc:sqlite:applicationDb.db";
	private static String usernameTable = "userInfoTable";
	private static String accountTable = "accountInfoTable";
	public static void main(String[] args)
	{
		String databaseName = "John";
		
		
		try 
		{
			
			initializeDatabase();
			Connection connection = DriverManager.getConnection(jdbcUrl);				
			java.sql.Statement statement = connection.createStatement();			
//=======================================================================================================			
			// test create new account
		     createNewUser("Minh Hung Le", "122345", "Who am I?", "John");
		     createNewUser("Van Chuong", "122345", "Who am I?", "Chuong");
		     createNewUser("ABC", "122345", "Who am I?", "ABC");
		     updateUserInfo("ABC", "2222", "How are you?", "I am good" );
		     createNewUser("Minh Hung Le", "122345", "Who am I?", "John");
		     createNewUser("Minh Hung Le", "122345", "Who am I?", "John");
		     
		     deleteUser("VanChuong");
					 
		     String selectAllUsersql = "SELECT * FROM " + usernameTable;
		     statement = connection.createStatement();			
			 ResultSet result = statement.executeQuery(selectAllUsersql);
			 
			 
			 while (result.next())
			 {
				 
				String uniqueID = result.getString("userID");
				String name = result.getString("username");
				String password = result.getString("userPassword");
				String question = result.getString("secQuestion");
				String answer = result.getString("answer");
				
				System.out.println(uniqueID + "  |  " + name + "  |  " + password + " | " + question +  "  |  " + answer);
			 }
			
//=============================================================================================================
			 //Test adding new account
			 
			addNewAccount("Minh Hung Le", "Google", "username1","password1", "email1","04/12/22","04/12/22","100");
			addNewAccount("Minh Hung Le", "Google", "username2","password1", "email1","04/12/22","04/12/22","100");
			addNewAccount("Minh Hung Le", "Google", "username3","password1", "email1","04/12/22","04/12/22","100");
			addNewAccount("Minh Hung Le", "Google", "username1","password1", "email1","04/12/22","04/12/22","100");
			addNewAccount("Minh Hung Le", "Google", "username1","password1", "email1","04/12/22","04/12/22","100");

			
			
			// Selecting database
			String selectAllAccountSql = "SELECT * FROM " + accountTable;
			statement = connection.createStatement();			
			result = statement.executeQuery(selectAllAccountSql);


			// loop through result;
			while (result.next())
			{
			
				String userID = result.getString("userID");
				String accID = result.getString("accountID");
				String applicationName = result.getString("appName");
				String accountName = result.getString("accountUsername");
				String password = result.getString("accountPass");
				
				System.out.println(userID + "  |  "+ accID + "  |  " +applicationName + "  |  " + accountName + " | " + password );
			}
			
			/*
			System.out.println("===============================================");
			result = searchAccount("Jim", "","test1"); 
			
			while (result.next())
			{
				String applicationName = result.getString("applicationName");
				String accountName = result.getString("accountName");
				String password = result.getString("password");
				
				System.out.println(applicationName + "  |  " + accountName + " | " + password);
			}
			
			 String path = System.getProperty("user.dir");
		        
		     System.out.println("Working Directory = " + path);
		
		     
		     //================================
		     
				*/
			 
		} 
		catch (SQLException e) 
		{
			System.out.println("Error connecting to SQLite Database");
			
			e.printStackTrace();
		}
		
	}
	
	
	
	private static void initializeDatabase() 
	{
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl);		
			java.sql.Statement statement = connection.createStatement();
			String userTablesql = "CREATE TABLE IF NOT EXISTS " + usernameTable
					+"(userID varchar(32) PRIMARY KEY, " 
					+ "username varchar(64), "
					+"userPassword varchar(64), "
					+"secQuestion varchar(256), "
					+"answer varchar(256))";  
			
			statement.executeUpdate(userTablesql);
			
			
			String accountTablesql = "CREATE TABLE IF NOT EXISTS " + accountTable + "("
					+"userID varchar(32), "
					+"accountID varchar(32) PRIMARY KEY, "
					+"appName varchar(64), " 
					+"accountUsername varchar(64), "
					+"accountPass varchar(64), "
					+"email varchar(64), "
					+"dateCreated varchar(64), "    
					+"dateExpire varchar(64), "  
					+"duration varchar(64), "
					+"FOREIGN KEY (userID) REFERENCES " + usernameTable + "(userID)"
					+")";
	
			statement.executeUpdate(accountTablesql);
			
			connection.close();
		} 
		catch (SQLException e) 
		{
			System.out.println("Error initialize database");
			
			e.printStackTrace();
		}
	}

	
	
	
	/***
	 * This method adding a new account into user table based on the user name
	 */
	private static void addNewAccount(String username, String appName, String accountUsername,
			String accountPass, String email, String dateCreated, String dateExpire, String duration) 
	{
		try 
		{
			// check if account already exit
			if (!isUserExist(username))
			{
				return;	
			}
			
			// get username ID
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement(); 
			String searchAccountSql = "SELECT * FROM " + usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
					
			ResultSet result = statement.executeQuery(searchAccountSql);
			
			String userID = result.getString("userID");			


			if (isAccountExist(userID, appName, accountUsername))
			{
				return;
			}
			
			
			
			
			
			//java.sql.Statement statement = connection.createStatement();
			
			String accountID = UUID.randomUUID().toString().replace("-", "");
			String addAccountsql = "INSERT INTO " + accountTable + 
					"(userID, accountID, appName, accountUsername, accountPass, "
					+ "email, dateCreated, dateExpire, duration) " + "\n" + 
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			
			// add data to table
	        PreparedStatement addAccountPstmt = connection.prepareStatement(addAccountsql);
		
	        addAccountPstmt.setString(1, userID);
	        addAccountPstmt.setString(2, accountID);
	        addAccountPstmt.setString(3, appName);
	        addAccountPstmt.setString(4, accountUsername);
	        addAccountPstmt.setString(5, accountPass);
	        addAccountPstmt.setString(6, email);
	        addAccountPstmt.setString(7, dateCreated);
	        addAccountPstmt.setString(8, dateExpire);
	        addAccountPstmt.setString(9, duration);
	        
	        addAccountPstmt.executeUpdate();
	        connection.close();


		}
		catch (SQLException e) 
		{
			System.out.println("Error in creating new Account");
			
			e.printStackTrace();	
		}		
	}
	
	
	/***
	 * This method adding a new user into database
	 */
	private static void createNewUser(String username, String userPassword, String secQuestion, String answer)
	{
		try 
		{
			
			if (isUserExist(username))
			{
				return; 
			}
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			
	        // adding new user name into table
	        String createNewUserSql = "INSERT INTO " + usernameTable 
	        		+"(userID, username, userPassword, secQuestion, answer) " + "\n" 
	        		+"VALUES(?, ?, ?, ?, ?)";
			
			// add data to table
	        PreparedStatement createNewUserPs = connection.prepareStatement(createNewUserSql);
	        String userID = UUID.randomUUID().toString().replace("-", "");
	        createNewUserPs.setString(1, userID);
	        createNewUserPs.setString(2, username);
	        createNewUserPs.setString(3, userPassword);
	        createNewUserPs.setString(4, secQuestion); 
	        createNewUserPs.setString(5, answer); 
	        createNewUserPs.executeUpdate();
	
		}
		catch (SQLException e) 
		{
			System.out.println("Error in creating new Users");
			
			e.printStackTrace();	
		}
		
	}
	
	
	/**
	 * This method search for an account that match application name or accountName
	 * @param username
	 * @param applicationName
	 * @param accountName
	 * @return
	 */
	private static ResultSet searchAccount(String userID, String appName, String accountUsername)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + accountTable
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					+ " AND " 
					
					+ "(appName = '" + appName + "'" + " COLLATE NOCASE "
					+ " OR " 
					+ "accountUsername = '" + accountUsername + "'" + " COLLATE NOCASE)";
				
			
			result = statement.executeQuery(searchAccountSql);
			
			
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account by userID, appName and accountUserName");
			
			e.printStackTrace();	
		}
		
		return  result;
	}
	
	/**
	 * This method search Account by user ID and accountID
	 * @param userID
	 * @param accountID
	 * @return
	 */
	private static ResultSet searchAccount(String userID, String accountID)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + accountTable
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					+ "accountID = '" + accountID + "'";
			statement.executeQuery(searchAccountSql);
			
			
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account by User ID and Account ID");
			
			e.printStackTrace();	
		}
		
		return  result;
	}
	
	private static ResultSet searchUser(String username)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String searchAccountSql = "SELECT * FROM " + usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";
					
			result = statement.executeQuery(searchAccountSql);
			
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching username");
			
			e.printStackTrace();	
		}
		
		return  result;
	}
	
	private static void deleteUser(String username)
	{
		try 
		{
			
			if (!isUserExist(username))
			{
				return;
			}
			
			// Delete all account binds to user
			String userID = searchUser(username).getString("userID");
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String deleteUserAllAccountSql = "DELETE FROM " + accountTable
					+ " WHERE " 				
					+ "userId = '" + userID + "'";
			statement.execute(deleteUserAllAccountSql);
			
			
			// Delete user
			String deleteUserSql = "DELETE FROM " + usernameTable
					+ " WHERE " 				
					+ "userID = '" + userID + "'";
								
			
			statement.execute(deleteUserSql);
			
		
		}
		catch  (SQLException e) 
		{
			System.out.println("Error in delete user");	
			e.printStackTrace();	
		}
	}

	/***
	 * This Method delete an account from current user name table
	 */
	private static void deleteAccount(String userID, String appName, String accountUsername)
	{
		try 
		{
			
			if (!isAccountExist(userID, appName, accountUsername))
			{
				return;
		
			}
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String deleteAccountSql = "DELETE FROM " + accountTable
					+ " WHERE " 
					
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "appName = '" + appName + "'"
					
					+ " AND " 
			
					+ "accountUsername = '" + accountUsername + "'";
				
			
			statement.execute(deleteAccountSql);
		}
		catch  (SQLException e) 
		{
			System.out.println("Error in delete account");	
			e.printStackTrace();	
		}
		
		
	}

	/**
	 * This method delete account by user ID and accountID
	 * @param userID
	 * @param accountID
	 */
	private static void deleteAccount(String userID, String accountID)
	{
		try 
		{
			/*
			if (!isAccountExist(username, applicationName, accountName))
			{
				return;
			}
			*/
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String deleteAccountSql = "DELETE FROM " + accountTable
					+ " WHERE " 
					
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accountID + "'";
			
			statement.execute(deleteAccountSql);
		}
		catch  (SQLException e) 
		{
			System.out.println("Error in delete account by user ID and Account ID");	
			e.printStackTrace();	
		}
		
		
	}
	
	/**
	 * This method check whether or not an account already created by user
	 * @param username
	 * @param applicationName
	 * @param accountName
	 * @return
	 */	
	private static boolean isAccountExist(String userID, String appName, String accountUsername)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			
			// Find if account already exist
			String sql = "SELECT * FROM " + accountTable
					+ " WHERE " 
					
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "appName = '" + appName + "'"
					
					+ " AND " 
					
					+ "accountUsername = '" + accountUsername + "'";
			
			result = statement.executeQuery(sql);
			
			if (result.next())
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
	 *  This method check if user already exist
	 * @param username
	 * @return
	 */
	private static boolean isUserExist(String username)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
	


			// Find if account already exist
			String findUserSql = "SELECT * FROM " + usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";

			
			result = statement.executeQuery(findUserSql);
			
			if (result.next())
			{
				connection.close();
				return true;
			}
			connection.close();
		}
		catch (SQLException e) 
		{
			System.out.println("Error in checking if user exists");
			
			e.printStackTrace();	
		}
		
		return false;
	}

	
	private static void updateAccountInfo(String userID, String appName, String accountUsername, String newApplicationName, String newAccountName,
			String newPassword, String newEmail, String newCreationDay, String newDuration)
	{
		try 
		{
			if (isAccountExist(userID, newApplicationName, newAccountName))
			{
				return;
			}
			String accountID = searchAccount(userID, appName, accountUsername).getString("accountID");
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + accountTable
					+ " SET " 
					+ "applicationName = '" + newApplicationName + "' , " 
					+ "accountName = '" + newAccountName + "' , "
					+ "password = '" + newPassword + "' , "
					+ "email = '" + newEmail + "' , "
					+ "creationDay = '" + newCreationDay +  "' , "
					+ "duration = '" + newDuration +  "' "
					
					+ " WHERE " 
					+ "userID = '" + userID + "'"
					
					+ " AND " 
					
					+ "accountID = '" + accountID + "'";
			
			statement.executeUpdate(sql);

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing account");
			
			e.printStackTrace();	
		}
	}

	
	private static void updateUserInfo(String username, String newPassword, String newQuestion, String newAnswer)
	{
		try 
		{
			if (!isUserExist(username))
			{
				return;
			}
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + usernameTable
					+ " SET " 
					+ "userPassword = '" + newPassword + "' , " 
					+ "secQuestion = '" + newQuestion + "' , "
					+ "answer = '" + newAnswer + "'"

					+ " WHERE " 
					+ "username = '" + username + "'";
			
			statement.executeUpdate(sql);

		}
		catch (SQLException e) 
		{
			System.out.println("Error in editing user account");
			
			e.printStackTrace();	
		}
	}

	
}



