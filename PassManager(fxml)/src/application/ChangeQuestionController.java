package application;
import java.io.IOException;

import DAO.UpdateUserDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class ChangeQuestionController extends AppUI {
	
	@FXML
	private TextArea oldQuest;
	@FXML
	private TextField newQuest;
	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() 
	{
		oldQuest.setText(Settings.currentUser.getSecQuestion());
		oldQuest.setWrapText(true);
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_submit(ActionEvent event) throws IOException 
	{
		int userID = Settings.currentUser.getUserID();
		String newQuestString = newQuest.getText().trim();
		
		if (newQuestString.equals(""))
		{
			alretMessege("Question cannot be empty!");
			return;
		}
		//save new pass and update the database
		UpdateUserDAO.UpdateUserSecQuestion(userID, newQuestString);
		Settings.currentUser = UpdateUserDAO.getUser(userID);
		alretMessege("Sucessful change security question!");
		changeScene(event, Settings.UserProfileScene);
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event, Settings.UserProfileScene);
	}		
}
