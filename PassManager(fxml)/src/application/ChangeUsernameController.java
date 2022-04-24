package application;
import java.io.IOException;

import DAO.UpdateAccountDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class ChangeUsernameController extends AppUI{

	@FXML
	private TextField oldUsername;
	@FXML
	private TextField newUsername;

	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		//we have to show the old user name in screen
		oldUsername.setText(Settings.selectedAccount.getUserName());
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_submit(ActionEvent event) throws IOException {
		//save new pass and update the database
		String newUsernameString = newUsername.getText().trim();
		String appNameString = Settings.selectedAccount.getAppName();
		int accID = Settings.selectedAccount.getAccID();
		int userID = Settings.currentUser.getUserID();
		
		// Check if new application name is empty
		if (newUsernameString.equals(""))
		{
			alretMessege("username cannot be empty");
			return;
		}
		
		
		// verify if account exist
		boolean isAccountExist = UpdateAccountDAO.isAccountExist(userID, appNameString, newUsernameString);
		if (isAccountExist)
		{
			alretMessege("application and account name pair already exist! Please choose new username");
			return;
		}
		
		UpdateAccountDAO.updateAccountUsername(userID, accID, newUsernameString);
		Settings.selectedAccount = UpdateAccountDAO.getAccount(userID, accID);
		changeScene(event, Settings.EditingAccountScene);
	}

	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException 
	{
		changeScene(event, Settings.EditingAccountScene);
	}	
}