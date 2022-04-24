package application;

import java.io.IOException;

import DAO.LoginDAO;
import DAO.ResetPasswordDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AskUsernameController extends AppUI {
	private final String fxml1 = "ResetPassQuestionMenu.fxml";
	private final String fxml2 = "LoginMenu.fxml";
	@FXML
	private TextField username;
	@FXML



	public void click_submit(ActionEvent event) throws IOException 
	{
		String userString = username.getText().trim();
		
		User currentUser =  LoginDAO.getUser(userString);
		if (currentUser != null)
		{
			Settings.currentUser = currentUser;
			changeScene(event,fxml1);
		}
		else
		{
			alretMessege("Username does not exist!!!");
		}
		
	}
	
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}
}
