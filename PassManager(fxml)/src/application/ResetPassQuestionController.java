package application;

import java.io.IOException;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResetPassQuestionController extends AppUI  {

	@FXML
	private TextArea username;
	@FXML
	private TextArea quest;
	@FXML
	private  TextField ans;
	
	@FXML
	private void initialize()
	{
		String usernameString = Settings.currentUser.getUserName();
		String questString = Settings.currentUser.getSecQuestion();
		
		username.setText(usernameString);
		quest.setText(questString);
	}
	
	@FXML
	public void click_submit(ActionEvent event) throws IOException 
	{
		User user = Settings.currentUser;
		String answer = user.getAnswer();
		String inputAnswer = ans.getText().trim();
		
		if (answer.equals(inputAnswer))
		{
			changeScene(event, Settings.ResetLogInPasswordScene);
			return;
		}
		
		alretMessege("Incorrect answer. Please retry!");
	}
	
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException 
	{
		changeScene(event, Settings.LoginScene);
	}

}
