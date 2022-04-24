package application;
import java.io.IOException;

import DAO.UpdateAccountDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class ChangeEmailController extends AppUI{

	@FXML
	private TextField oldEmail;
	@FXML
	private TextField newEmail;
	@FXML
	private TextField repeateEmail;

	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		oldEmail.setText(Settings.selectedAccount.getEmail());
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void clickSubmit(ActionEvent event) throws IOException
	{
		String newEmailString = newEmail.getText().trim();
		String repeatedEmailString = repeateEmail.getText().trim();
		int accID = Settings.selectedAccount.getAccID();
		int userID = Settings.currentUser.getUserID();
		
		// Check if new application name is empty
		if (!newEmailString.equals(repeatedEmailString))
		{
			alretMessege("Email and repeated Email do not match!");
			return;
		}
				
		UpdateAccountDAO.updateAccountEmail(userID, accID, newEmailString);
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
