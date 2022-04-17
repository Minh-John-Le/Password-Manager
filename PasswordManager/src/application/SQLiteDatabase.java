
package application;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CORBA.Environment;

public class SQLiteDatabase {
	private static String jdbcUrl = "jdbc:sqlite:applicationDb.db";
	private static String usernameTable = "usernameTable";
	public static void main(String[] args)
	{
		String databaseName = "John";
		
		
		try {
			
			
			Connection connection = DriverManager.getConnection(jdbcUrl);		
			String sql = "CREATE TABLE IF NOT EXISTS " + databaseName + "(firstname varchar(20), lastname varchar(20))";			
			java.sql.Statement statement = connection.createStatement();			
			statement.executeUpdate(sql);
			
			
			createNewAccount("Jim", "Google", "Test1", "123456","10", "", "100");
			createNewAccount("Jim", "google", "test2", "123456","10", "", "100");
			createNewAccount("Jim", "GOOGLE", "Test3", "123456","10", "", "100");
			createNewAccount("Jim", "GOOGLE", "Test4", "123456","10", "", "100");
			
			deleteAccount("Jim", "GOOGLE", "Test4");
			
			
			updateAccountInfo("Jim", "Google", "Test1", "AWS", "Test1", "123456","10", "", "100" );
			
			// Selecting database
			sql = "SELECT * FROM " + "Jim";
			statement = connection.createStatement();			
			ResultSet result = statement.executeQuery(sql);
			
			
			// loop through result;
			while (result.next())
			{
				String applicationName = result.getString("applicationName");
				String accountName = result.getString("accountName");
				String password = result.getString("password");
				
				System.out.println(applicationName + "  |  " + accountName + " | " + password);
			}
			
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
		     // test create new account
		     createNewUser("MinhHungLe", "122345", "Who am I?", "John");
		     createNewUser("VanChuong", "122345", "Who am I?", "Chuong");
		     createNewUser("ABC", "122345", "Who am I?", "ABC");
		     updateUserInfo("ABC", "2222", "How are you?", "I am good" );
		     
		     deleteUser("VanChuong");
					 
		     sql = "SELECT * FROM " + usernameTable;
		     statement = connection.createStatement();			
			 result = statement.executeQuery(sql);
			 
			 
			 while (result.next())
				{
					String name = result.getString("username");
					String password = result.getString("password");
					String question = result.getString("question");
					
					System.out.println(name + "  |  " + password + " | " + question);
				}
				
			 
		} 
		catch (SQLException e) 
		{
			System.out.println("Error connecting to SQLite Database");
			
			e.printStackTrace();
		}
		
	}
	
	
	
	/***
	 * This method adding a new account into user table based on the user name

	 */
	private static void createNewAccount(String username, String applicationName, String accountName,
			String password, String email, String creationDay, String duration) 
	{
		try 
		{
			// check if account already exit
			if (isAccountExist(username, applicationName, accountName))
			{
				return;
			}
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			
			
			// command for new table if table not exist yet
			String sql = "CREATE TABLE IF NOT EXISTS " + username +
					"(applicationName varchar(64), accountName varchar(64), password varchar(64),"
					+ " email varchar(64), creationDay varchar(64), duration varchar(10))";
			
			statement.executeUpdate(sql);
			
			sql = "INSERT INTO " + username + "(applicationName, accountName, password, email, creationDay, duration) " + "\n" + 
					"VALUES(?,?,?,?,?,?)";
			
			
			// add data to table
	        PreparedStatement pstmt = connection.prepareStatement(sql);
		
			pstmt.setString(1, applicationName);
	        pstmt.setString(2, accountName);
	        pstmt.setString(3, password);
	        pstmt.setString(4, email);
	        pstmt.setString(5, creationDay);
	        pstmt.setString(6, duration);
	        
	        pstmt.executeUpdate();
	        

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
	private static void createNewUser(String username, String password, String question, String answer)
	{
		try 
		{
			
			if (isUserExist(username))
			{
				return; 
			}
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			// Create a new table in data base if need
			String sql = "CREATE TABLE IF NOT EXISTS usernameTable("
					+ "username varchar(64),"
					+ "password varchar(64),"
					+ "question varchar(64),"
					+ "answer varchar(64))";		


	        statement.executeUpdate(sql);        
	        
	        // adding new user name into table
	        sql = "INSERT INTO " + usernameTable + "(username, password, question, answer) " + "\n" + 
					"VALUES(?,?,?,?)";
			
			// add data to table
	        PreparedStatement pstmt = connection.prepareStatement(sql);		
			pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        pstmt.setString(3, question);
	        pstmt.setString(4, answer);    
	        pstmt.executeUpdate();
	
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
	private static ResultSet searchAccount(String username,String applicationName, String accountName)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "SELECT * FROM " + username
					+ " WHERE " 
					+ "applicationName = '" + applicationName + "'" + " COLLATE NOCASE "
					
					+ " OR " 
					
					+ "accountName like '" + accountName + "'" + " COLLATE NOCASE ";
				
			
			result = statement.executeQuery(sql);
			
			
		}
		catch (SQLException e) 
		{
			System.out.println("Error in searching account");
			
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
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "DELETE FROM " + usernameTable
					+ " WHERE " 
					
					+ "username = '" + username + "'";
								
			
			statement.execute(sql);
			
			sql = "DROP TABLE IF EXISTS " + username;
			statement = connection.createStatement();			
			statement.execute(sql);
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
	private static void deleteAccount(String username, String applicationName, String accountName)
	{
		try 
		{
			
			if (!isAccountExist(username, applicationName, accountName))
			{
				return;
			}
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "DELETE FROM " + username
					+ " WHERE " 
					
					+ "applicationName = '" + applicationName + "'"
					
					+ " AND " 
					
					+ "accountName = '" + accountName + "'";
				
			
			statement.execute(sql);
		}
		catch  (SQLException e) 
		{
			System.out.println("Error in delete account");	
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
	private static boolean isAccountExist(String username, String applicationName, String accountName)
	{
		ResultSet result = null;
		try 
		{
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			// command for new table if table not exist yet
			String sql = "CREATE TABLE IF NOT EXISTS " + username +
					"(applicationName varchar(64), accountName varchar(64), password varchar(64),"
					+ " email varchar(64), creationDay varchar(64), duration varchar(10))";
			
			statement.executeUpdate(sql);
			
			// Find if account already exist
			sql = "SELECT * FROM " + username
					+ " WHERE " 
					+ "applicationName = '" + applicationName + "'"
					
					+ " AND " 
					
					+ "accountName = '" + accountName + "'";
			
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
			
			// Create a new table in data base if need
			String sql = "CREATE TABLE IF NOT EXISTS " + usernameTable + "("
					+ "username varchar(64),"
					+ "password varchar(64),"
					+ "question varchar(64),"
					+ "answer varchar(64))";		


			statement.executeUpdate(sql);
			// Find if account already exist
			sql = "SELECT * FROM " + usernameTable
					+ " WHERE " 
					+ "username = '" + username + "'";

			
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
			System.out.println("Error in checking if user exists");
			
			e.printStackTrace();	
		}
		
		return false;
	}

	
	private static void updateAccountInfo(String username, String applicationName, String accountName, String newApplicationName, String newAccountName,
			String newPassword, String newEmail, String newCreationDay, String newDuration)
	{
		try 
		{
			if (isAccountExist(username, newApplicationName, newAccountName))
			{
				return;
			}
			
			Connection connection = DriverManager.getConnection(jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String sql = "UPDATE " + username
					+ " SET " 
					+ "applicationName = '" + newApplicationName + "' , " 
					+ "accountName = '" + newAccountName + "' , "
					+ "password = '" + newPassword + "' , "
					+ "email = '" + newEmail + "' , "
					+ "creationDay = '" + newCreationDay +  "' , "
					+ "duration = '" + newDuration +  "' "
					
					+ " WHERE " 
					+ "applicationName = '" + applicationName + "'"
					
					+ " AND " 
					
					+ "accountName = '" + accountName + "'";
			
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
					+ "password = '" + newPassword + "' , " 
					+ "question = '" + newQuestion + "' , "
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



