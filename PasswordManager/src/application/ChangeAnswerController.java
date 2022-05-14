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
	
	
	/**
	 * This method initialize the scene with old answer from database
	 */
	@FXML 
	public void initialize() {
		oldAns.setText(Settings.currentUser.getAnswer());
	}

	
	/**
	 * This method update user new answer for master profile
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_submit(ActionEvent event) throws IOException 
	{
		//save new pass and update the database
		int userID = Settings.currentUser.getUserID();
		String newAnsString = newAns.getText().trim();
		
		
		if (newAnsString.equals(""))
		{
			alretMessege("Answer cannot be empty!");
			return;
		}
		//save new pass and update the database
		UpdateUserDAO.UpdateUserAnswer(userID, newAnsString);
		Settings.currentUser = UpdateUserDAO.getUser(userID);
		alretMessege("Sucessful change security answer!");
		changeScene(event, Settings.UserProfileScene);
	}
	
	/**
	 * This method return user back to previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event, Settings.UserProfileScene);
	}
}
