package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import GeneralSettings.Settings;


public class InitializeDAO {
	
	public static void initializeDatabase() 
	{
		try {
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);		
			java.sql.Statement statement = connection.createStatement();
			String userTablesql = "CREATE TABLE IF NOT EXISTS " + Settings.usernameTable
					+"(userID INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ "username varchar(64), "
					+"userPassword varchar(64), "
					+"secQuestion varchar(256), "
					+"answer varchar(256))";  
			
			statement.executeUpdate(userTablesql);
			
			
			String accountTablesql = "CREATE TABLE IF NOT EXISTS " + Settings.accountTable + "("
					+"userID INTEGER, "
					+"accountID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+"appName varchar(64), " 
					+"accountUsername varchar(64), "
					+"accountPass varchar(64), "
					+"email varchar(64), "
					+"dateCreated varchar(64), "    
					+"dateExpire varchar(64), "  
					+"duration varchar(64), "
					+"FOREIGN KEY (userID) REFERENCES " + Settings.usernameTable + "(userID)"
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
	
}
