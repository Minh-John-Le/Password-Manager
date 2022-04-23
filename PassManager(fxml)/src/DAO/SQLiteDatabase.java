
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;
import java.sql.Statement;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import application.Account;
import application.User;

public class SQLiteDatabase {
	private static String jdbcUrl = "jdbc:sqlite:applicationDb.db";
	private static String usernameTable = "userInfoTable";
	private static String accountTable = "accountInfoTable";
	public static void main(String[] args)
	{
		String databaseName = "John";
		
		
		try 
		{
			InitializeDAO.initializeDatabase(); 
				
//=======================================================================================================			
			// test create new account
		     SignUpDAO.createNewUser("Minh Hung Le", "122345", "Who am I?", "John");
		     SignUpDAO.createNewUser("Van Chuong", "122345", "Who am I?", "Chuong");
		     SignUpDAO.createNewUser("ABC", "122345", "Who am I?", "ABC");
		     SignUpDAO.createNewUser("ABC", "2222", "How are you?", "I am good" );
		     SignUpDAO.createNewUser("Minh Hung Le", "122345", "Who am I?", "John");
		     SignUpDAO.createNewUser("Minh Hung Le", "122345", "Who am I?", "John");
		     
		     User currentUser = LoginDAO.getUser("1231");
		     
		    // System.out.println("This is user pass = " + currentUser.getUserPass());
		     
		     Connection connection = DriverManager.getConnection(jdbcUrl);				
				Statement statement = connection.createStatement();				 
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
			
			AddAccountDAO.addNewAccount(17, "Google", "username1","password1", "email1","04/12/22","04/12/22","100");
			AddAccountDAO.addNewAccount(17, "Google", "username2","password1", "email1","04/12/22","04/12/22","100");
			AddAccountDAO.addNewAccount(17, "Google", "username3","password1", "email1","04/12/22","04/12/22","100");
			AddAccountDAO.addNewAccount(17, "Google", "username1","password1", "email1","04/12/22","04/12/22","100");
			AddAccountDAO.addNewAccount(17, "Google", "username1","password1", "email1","04/12/22","04/12/22","100");
			//DeleteAccountDAO.deleteAccount(1,6);
			//DeleteUserDAO.deleteUser(1);
			
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
			
			
//==================================================================================
			
			ArrayList<Account> accounts = (SearchAccountDAO.getAccount(17,"google", "")); 
			
			for (int i = 0; i < accounts.size(); i++)
			{
				Account currentAccount = accounts.get(i);
				int userID = currentAccount.getUserID();
				int accID = currentAccount.getAccID();
				String applicationName = currentAccount.getAppName();
				String accountName = currentAccount.getUserName();
				String password = currentAccount.getAppPass();
				
				System.out.println(userID + "  |  " +  accID + "  |  " + applicationName + "  |  " + accountName + " | " + password);
			}
			
			 String path = System.getProperty("user.dir");
		        
		     System.out.println("Working Directory = " + path);
			
		     
				
			 
		} 
		catch (SQLException e) 
		{
			System.out.println("Error connecting to SQLite Database");
			
			e.printStackTrace();
		}
		
	}
	
}



