package application;

import java.io.IOException;

import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SettingController extends AppUI{
	private final String text = "Do you want to delete your master account?";
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextArea quest;
	@FXML
	private TextField ans;
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	@FXML public void initialize() {
		String usernameString = Settings.currentUser.getUserName();
		String passwordString = Settings.currentUser.getUserPass();
		String questString = Settings.currentUser.getSecQuestion();
		String ansString = Settings.currentUser.getAnswer();
		
		username.setText(usernameString);
		pass.setText(passwordString);
		
		quest.setText(questString);
		//this is so important to be in code. it let the question to fit properly in the textfield
		quest.setWrapText(true);
		ans.setText(ansString);
		}
	@FXML public void click_back(ActionEvent event) throws IOException 
	{
		// back to main menu
		changeScene(event, Settings.MainScene);
	}

	
	@FXML public void click_changePass(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"ChangePasswordMenu.fxml");
	}
	@FXML public void click_changeQuest(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"ChangeQuestionMenu.fxml");
	}
	@FXML public void click_changeAns(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"ChangeAnswerMenu.fxml");
	}
	@FXML public void click_delete(ActionEvent event) throws IOException {
		// back to main menu
		if(alretConfirmation(text)) {
			changeScene(event,"LoginMenu.fxml");
		}
	}
}