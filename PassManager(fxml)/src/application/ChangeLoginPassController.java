package application;

import java.io.IOException;

import DAO.ResetPasswordDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeLoginPassController extends AppUI {
	private final String fxml = "LoginMenu.fxml";
	@FXML
	private TextField oldPass;
	@FXML
	private TextField newPass;
	@FXML
	private TextField repeatePass;

	@FXML 
	public void initialize() 
	{
		User user = Settings.currentUser;
		oldPass.setText(user.getUserPass());
		
	}

	@FXML
	public void clickSubmit(ActionEvent event) throws IOException 
	{
		String newPassString = newPass.getText().trim();
		String repeatePassString = repeatePass.getText().trim();
		int userID = Settings.currentUser.getUserID();
		
		if (newPassString.equals(repeatePassString))
		{	
			ResetPasswordDAO.updateUserPassword(userID, newPassString);
			alretMessege("Sucessful change password!");
			changeScene(event,fxml);
			return;
		}
		alretMessege("password and repeat password do not match!");
		
	}
	@FXML
	public void clickCancel(ActionEvent event) throws IOException {
		changeScene(event,fxml);
	}		

}
