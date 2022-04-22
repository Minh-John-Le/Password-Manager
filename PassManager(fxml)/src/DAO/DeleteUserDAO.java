package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import GeneralSettings.Settings;

public class DeleteUserDAO {
	
	public static void deleteUser(int userID)
	{
		try 
		{

			
			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String deleteUserAllAccountSql = "DELETE FROM " + Settings.accountTable
					+ " WHERE " 				
					+ "userId = '" + userID + "'";
			statement.execute(deleteUserAllAccountSql);
			
			
			// Delete user
			String deleteUserSql = "DELETE FROM " + Settings.usernameTable
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
		
		
	
}
