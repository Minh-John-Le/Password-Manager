package application;

import java.io.IOException;
import java.time.LocalDate;

import DAO.DeleteAccountDAO;
import DAO.UpdateAccountDAO;
import GeneralSettings.Settings;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditInfoController extends AppUI{
	private final String deleteWarningText = "Do you want to delete this record permanently?";
	@FXML
	private TextField appName;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField creationDate;
	@FXML
	private TextField expirationDate;
	@FXML
	private TextField day;
	
	// previous scene settings
	public static String previousScene = Settings.MainScene;


	/**
	 * This method fill in all data about the selected account
	 */
	@FXML 
	public void initialize() 
	{
		if (Settings.tempAccount == null)
		{
			return;
		}
		// get data from selected account
		String appNameString = Settings.tempAccount.getAppName();
		String emailString = Settings.tempAccount.getEmail();
		String usernameString = Settings.tempAccount.getUserName();
		String passwordString = Settings.tempAccount.getAppPass();
		String creationDateString = Settings.tempAccount.getDateCreated();
		String expirationDateString = Settings.tempAccount.getDateExpired();
		String durationString = Settings.tempAccount.getDuration();
		
		// Fill them in appropriate location
		appName.setText(appNameString);
		email.setText(emailString);
		username.setText(usernameString);
		pass.setText(passwordString);
		creationDate.setText(creationDateString);
		expirationDate.setText(expirationDateString);
		day.setText(durationString);
	}

	/**
	 * This method return user to previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_Back(ActionEvent event) throws IOException 
	{
		// reset the selected account to empty
		Settings.tempAccount = new Account();
		Settings.selectedAccount = new Account();

		changeScene(event, previousScene);
	}
	
	/***
	 * this method delete the account
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_Delete(ActionEvent event) throws IOException 
	{
		//ask for confirmation
		if(alretConfirmation(deleteWarningText))
		{
			DeleteAccountDAO.deleteAccount(Settings.selectedAccount.getUserID(), Settings.selectedAccount.getAccID());
			changeScene(event, previousScene);
		}
		
	}
	
	/**
	 * This method verified and update user new information about account
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_submit(ActionEvent event) throws IOException {
		//get data from text field
		String appNameString = appName.getText().trim();
		String userNameString = username.getText().trim();
		String emailString = email.getText().trim();
		String passString = pass.getText().trim();
		String durationString = day.getText().replaceAll("[^0-9]", "").trim();
		
		int userID = Settings.currentUser.getUserID();
		int accID = Settings.selectedAccount.getAccID();
		
		//=====================================================================================
		// Validation check
		if (appNameString.equals(""))
		{
			alretMessege("Application cannot be empty!");
			return;
		}
		
		if (durationString.equals(""))
		{
			alretMessege("Duration cannot be empty!");
			return;	
		}
		
		if (durationString.length() < day.getText().length())
		{
			alretMessege("Duration cannot contain any character or be negative!");
			return;
		}
		
		if (userNameString.equals(""))
		{
			alretMessege("Username cannot be empty!");
			return;
		}
		
		if (passString.equals(""))
		{
			alretMessege("Password cannot be empty!");
			return;
		}
		
		//=====================================================================================
		// verify if account exist
		boolean isAccountExist = UpdateAccountDAO.isAccountExist(userID, appNameString, userNameString);
		
		// if old user name then we don't check for account Exist
		if (Settings.selectedAccount.getAppName().equals(appNameString)
			&& Settings.selectedAccount.getUserName().equals(userNameString))
		{
			isAccountExist = false;
		}
		
		if (isAccountExist)
		{
			alretMessege("Application and account name pair already exist! Please choose new application or username name");
			return;
		}
		
		
		//=====================================================================================
		// Update account in database
		UpdateAccountDAO.updateAccountAppName(userID, accID, appNameString);
		UpdateAccountDAO.updateAccountUsername(userID, accID, userNameString);
		UpdateAccountDAO.updateAccountEmail(userID, accID, emailString);
		UpdateAccountDAO.updateAccountDuration(userID, accID, durationString);
		
		
		// update the password and created only if user change password
		if (!passString.equals(Settings.selectedAccount.getAppPass()))
		{
			updatePasswordInfomation(userID, accID, passString);
			
		}
		
		// Reset selected account to empty and go back to previous page
		Settings.tempAccount = new Account();
		Settings.selectedAccount = new Account();
		alretMessege("Application information was edited!");
		changeScene(event, previousScene);
	}
	
	/**
	 * This method get user to password generator page
	 * @param event
	 * @throws IOException
	 */
	@FXML public void click_generatePass(ActionEvent event) throws IOException {
		// gett data from textfield
		String appNameString = appName.getText().trim();
		String userNameString = username.getText().trim();
		String emailString = email.getText().trim();
		String passString = pass.getText().trim();
		String durationString = day.getText().trim();
		
		// Save those data to tempt account, 
		//so that when getting back from other page, user input still remain
		Account tempAccount = Settings.tempAccount;
		tempAccount.setAppName(appNameString);
		tempAccount.setUserName(userNameString);
		tempAccount.setEmail(emailString);
		tempAccount.setAppPass(passString);
		tempAccount.setDuration(durationString);
		
		GeneratePassController.previousScene = Settings.EditingAccountScene;
		changeScene(event, Settings.PassGeneratorScene);
	}
	
	/**
	 * This method update the password, create day and expire day
	 * @param userID
	 * @param accID
	 * @param newPassString
	 */
	private void updatePasswordInfomation(int userID, int accID, String newPassString )
	{
		LocalDate today = LocalDate.now();	
		String durationString = day.getText();
		
		int duration = Integer.parseInt(durationString);
		LocalDate future = LocalDate.now().plusDays(duration);		
		
		String newCreatedDay = today.toString().replace("-", "/");
		String newExpiredDay = future.toString().replace("-", "/");
			
		
		// update password , created day and expired day
		UpdateAccountDAO.updateAccountPassword(userID, accID, newPassString);
		UpdateAccountDAO.updateAccountCreationDay(userID, accID, newCreatedDay);
		UpdateAccountDAO.updateAccountExpiredDay(userID, accID, newExpiredDay);
	}
}
