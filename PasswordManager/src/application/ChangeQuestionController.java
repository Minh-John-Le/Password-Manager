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
	
	/**
	 * This method fill in old question textfield with the old question in database
	 */
	@FXML 
	public void initialize() 
	{
		oldQuest.setText(Settings.currentUser.getSecQuestion());
		oldQuest.setWrapText(true);
	}

	/**
	 * This method verify user new question and update new question on database
	 * @param event
	 * @throws IOException
	 */
	@FXML
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
	
	/**
	 * This method return user to previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event, Settings.UserProfileScene);
	}		
}
