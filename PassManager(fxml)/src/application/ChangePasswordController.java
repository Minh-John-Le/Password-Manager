package application;
import java.io.IOException;
import java.time.LocalDate;

import DAO.UpdateAccountDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class ChangePasswordController extends AppUI{
	

	@FXML
	private TextField oldPass;
	@FXML
	private TextField newPass;
	@FXML
	private TextField repeatePass;

	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		oldPass.setText(Settings.selectedAccount.getAppPass());
		newPass.setText(Settings.tempAccount.getAppPass());
		repeatePass.setText(Settings.tempAccount.getAppPass());
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void clickSubmit(ActionEvent event) throws IOException {
		String newPassString = newPass.getText().trim();
		String repeatNewPassString = repeatePass.getText().trim();
		int accID = Settings.selectedAccount.getAccID();
		int userID = Settings.currentUser.getUserID();
		
		// Check if new application name is empty
		if (newPassString.equals(""))
		{
			alretMessege("password cannot be empty");
			return;
		}
		
		
		if (!newPassString.equals(repeatNewPassString))
		{
			alretMessege("password and repeated password do not match!");
			return;
		}
		
		
		// calculate current day, duration and expired day
		LocalDate today = LocalDate.now();	
		String durationString = Settings.selectedAccount.getDuration();
		int duration = Integer.parseInt(durationString);
		
		
		LocalDate future = LocalDate.now().plusDays(duration);		
		String newCreatedDay = today.toString().replace("-", "/");
		String newExpiredDay = future.toString().replace("-", "/");
			
		
		// update password , created day and expired day
		UpdateAccountDAO.updateAccountPassword(userID, accID, newPassString);
		UpdateAccountDAO.updateAccountCreationDay(userID, accID, newCreatedDay);
		UpdateAccountDAO.updateAccountExpiredDay(userID, accID, newExpiredDay);
		
		Settings.selectedAccount = UpdateAccountDAO.getAccount(userID, accID);
		Settings.tempAccount = new Account(-1,-1, "","","","","","","");
		changeScene(event, Settings.EditingAccountScene);
	}
	@FXML
	//it returns back app info menu
	public void clickCancel(ActionEvent event) throws IOException 
	{
		Settings.tempAccount = new Account(-1,-1, "","","","","","","");
		changeScene(event, Settings.EditingAccountScene);
	}		
	@FXML
	//it returns back app info menu
	public void clickGenerator(ActionEvent event) throws IOException 
	{
		Settings.tempAccount.setAppPass(newPass.getText());
		GeneratePassController.previousScene = "ChangePasswordMenu.fxml";
		changeScene(event, Settings.PassGeneratorScene);
	}


}