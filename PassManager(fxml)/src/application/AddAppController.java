package application;

import java.io.IOException;

import DAO.AddAccountDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAppController extends AppUI{
	private final String fxml1 = "GeneratePassMenu.fxml";
	private final String fxml2 = "MainMenu.fxml";
	private final User user = LoginController.user;
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField repeatedPass;
	@FXML
	private TextField email;
	@FXML
	private TextField duration;
	@FXML
	private TextField appName;
	

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to main menu after deleting the data
	public void click_submit(ActionEvent event) throws IOException {
		int userID = user.getUserID();
		String newAccountUsername = this.username.getText();
		String newPass = this.pass.getText();
		String newRepeatedPass = this.repeatedPass.getText();
		String newEmail = this.email.getText();
		String newDuration = this.duration.getText();
		String newAppName	 = this.appName.getText();
		String newDateCreated = "???";
		String newDateExpire ="???";
		if(AddAccountDAO.isAccountExist(userID, newAppName, newAccountUsername)) {
			alretMessege("Account Already Exsit !!!");
			return;
		}
		if(newPass.equals(newRepeatedPass)) {
			
			AddAccountDAO.addNewAccount(userID, newAppName, newAccountUsername, newPass, newEmail, newDateCreated, newDateExpire, newDuration);
			changeScene(event,fxml2);
		}
		else alretMessege("Password and RepeatedPass Don't Match !!!");
			
		
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}
	@FXML
	//it returns back app info menu
	public void click_generatePass(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}
	
}