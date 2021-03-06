package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import GeneralSettings.Settings;

public class DeleteAccountDAO {
	/** 
	 * this method remove an account from database
	 * @param userID
	 * @param accountID
	 */
	public static void deleteAccount(int userID, int accountID)
	{
		try 
		{

			Connection connection = DriverManager.getConnection(Settings.jdbcUrl);
			java.sql.Statement statement = connection.createStatement();
			
			String deleteAccountSql = "DELETE FROM " + Settings.accountTable
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
	

}
