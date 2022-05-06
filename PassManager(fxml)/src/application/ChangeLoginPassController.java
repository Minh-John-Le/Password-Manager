package application;

import java.io.IOException;

import DAO.ResetPasswordDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeLoginPassController extends AppUI {
	
	@FXML
	private TextField oldPass;
	@FXML
	private TextField newPass;
	@FXML
	private TextField repeatePass;

	public static String previousScene = Settings.LoginScene;
	
	/**
	 * This method fill in old password field with the user current password
	 */
	@FXML 
	public void initialize() 
	{
		User user = Settings.currentUser;
		oldPass.setText(user.getUserPass());
		
	}

	/**
	 * This method update user password
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void clickSubmit(ActionEvent event) throws IOException 
	{
		String newPassString = newPass.getText().trim();
		String repeatePassString = repeatePass.getText().trim();
		int userID = Settings.currentUser.getUserID();
		if (newPassString.equals(""))
		{
			alretMessege("Password cannot be empty!");
			return;
		}
		
		if (newPassString.equals(repeatePassString))
		{	
			ResetPasswordDAO.updateUserPassword(userID, newPassString);
			Settings.currentUser.setUserPass(newPassString);  
			alretMessege("Sucessful change password!");
			changeScene(event, previousScene);
			return;
		}
		alretMessege("password and repeat password do not match!");
		
	}
	
	
	/**
	 * This method return user to previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void clickCancel(ActionEvent event) throws IOException {
		changeScene(event, previousScene);
	}		

}
