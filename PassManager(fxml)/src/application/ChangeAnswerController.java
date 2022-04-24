package application;

import java.io.IOException;

import DAO.UpdateUserDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeAnswerController extends AppUI{

	@FXML
	private TextField oldAns;
	@FXML
	private TextField newAns;
	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		oldAns.setText(Settings.currentUser.getAnswer());
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_submit(ActionEvent event) throws IOException 
	{
		//save new pass and update the database
		int userID = Settings.currentUser.getUserID();
		String newAnsString = newAns.getText().trim();
		
		//save new pass and update the database
		UpdateUserDAO.UpdateUserAnswer(userID, newAnsString);
		Settings.currentUser = UpdateUserDAO.getUser(userID);
		changeScene(event, Settings.UserProfileScene);
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event, Settings.UserProfileScene);
	}
}
