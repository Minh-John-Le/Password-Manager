package application;

import java.io.IOException;

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

		username.setText("john_master");
		pass.setText("34254GQEf");
		
		quest.setText("What is you last time you went to a trip abroad?");
		//this is so important to be in code. it let the question to fit properly in the textfield
		quest.setWrapText(true);
		ans.setText("2021");
		}
	@FXML public void click_back(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"MainMenu.fxml");
	}
	@FXML public void click_changeUsername(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"ChangeUsernameMenu.fxml");
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