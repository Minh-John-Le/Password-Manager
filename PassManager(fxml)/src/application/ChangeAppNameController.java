package application;
import java.io.IOException;

import DAO.UpdateAccountDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class ChangeAppNameController extends AppUI{

	@FXML
	private TextField oldAppName;
	@FXML
	private TextField newAppName;

	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() 
	{
		String appNameString = Settings.selectedAccount.getAppName();
		oldAppName.setText(appNameString);
	}

	
	@FXML
	public void clickSubmit(ActionEvent event) throws IOException 
	{
		String newAppNameString = newAppName.getText().trim();
		String oldUserName = Settings.selectedAccount.getUserName();
		int accID = Settings.selectedAccount.getAccID();
		int userID = Settings.currentUser.getUserID();
		
		// Check if new application name is empty
		if (newAppNameString.equals(""))
		{
			alretMessege("application cannot be empty");
			return;
		}
		
		
		// verify if account exist
		boolean isAccountExist = UpdateAccountDAO.isAccountExist(userID, newAppNameString, oldUserName);
		if (isAccountExist)
		{
			alretMessege("application and account name pair already exist! Please choose new Application Name");
			return;
		}
		
		UpdateAccountDAO.updateAccountAppName(userID, accID, newAppNameString);
		Settings.selectedAccount = UpdateAccountDAO.getAccount(userID, accID);
		changeScene(event, Settings.EditingAccountScene);
	}

	@FXML
	//it returns back app info menu
	public void clickCancel(ActionEvent event) throws IOException 
	{
		changeScene(event, Settings.EditingAccountScene);
	}	
}