package application;

import java.io.IOException;

import DAO.DeleteAccountDAO;
import GeneralSettings.Settings;
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
	public static Account selectedAccount = null;


	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	@FXML 
	public void initialize() 
	{
		selectedAccount = Settings.selectedAccount;
		if (selectedAccount == null)
		{
			return;
		}
		
		String appNameString = selectedAccount.getAppName();
		String emailString = selectedAccount.getEmail();
		String usernameString = selectedAccount.getUserName();
		String passwordString = selectedAccount.getAppPass();
		String creationDateString = selectedAccount.getDateCreated();
		String expirationDateString = selectedAccount.getDateExpired();
		String durationString = selectedAccount.getDuration();
		
		
		appName.setText(appNameString);
		email.setText(emailString);
		username.setText(usernameString);
		pass.setText(passwordString);
		creationDate.setText(creationDateString);
		expirationDate.setText(expirationDateString);
		day.setText(durationString);
	}
	
	@FXML public void click_Back(ActionEvent event) throws IOException {
		// back to previos scene
		changeScene(event, previousScene);
	}
	
	/***
	 * this method delete the account
	 * @param event
	 * @throws IOException
	 */
	@FXML public void click_Delete(ActionEvent event) throws IOException {
		//ask for confirmation
		if(alretConfirmation(deleteWarningText))
		{
			DeleteAccountDAO.deleteAccount(selectedAccount.getUserID(), selectedAccount.getAccID());
			changeScene(event, previousScene);
		}
		
	}
	

	@FXML public void click_changeAppName(ActionEvent event) throws IOException {
		//open change App Name menu
		changeScene(event,"ChangeAppNameMenu.fxml");
	}
	
	@FXML public void click_changeEmail(ActionEvent event) throws IOException {
		//open change Email menu
		changeScene(event,"ChangeEmailMenu.fxml");
	}
	
	@FXML public void click_changeUsername(ActionEvent event) throws IOException {
		//open change Username menu
		changeScene(event,"ChangeUsernameMenu.fxml");
	}
	@FXML public void click_changePass(ActionEvent event) throws IOException {
		//open change Pass menu
		changeScene(event,"ChangePasswordMenu.fxml");
	}
	@FXML public void click_changeDuration(ActionEvent event) throws IOException {
		//open change Duration menu
	}
}
