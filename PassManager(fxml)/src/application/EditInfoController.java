package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditInfoController extends AppUI{
	final String text = "Do you want to delete this record permanently?";
	@FXML
	private TextField appName;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField creationDate;
	@FXML
	private TextField expirationDate;
	@FXML
	private TextField day;



	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	@FXML public void initialize() {
		appName.setText("Google");
		email.setText("john.smith@Gmail.com");
		username.setText("john_Smith78");
		pass.setText("yht34QEf");
		creationDate.setText("03/01/2022");
		expirationDate.setText("09/01/2022");
		day.setText("180");
		}
	@FXML public void click_Back(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"SearchMenu.fxml");
	}
	@FXML public void click_Delete(ActionEvent event) throws IOException {
		//ask for confirmation
		if(alretConfirmation(text)) {
		changeScene(event,"MainMenu.fxml");
		
		//logic behind deleting data and updating the database
		
		
		// or do nothing stay in app info
		}
	}
	

	@FXML public void click_changeAppName(ActionEvent event) throws IOException {
		//open change App Name menu
		changeScene(event,"ChangeAppNameMenu.fxml");
	}
	@FXML public void click_changeEmail(ActionEvent event) throws IOException {
		//open change Email menu
		changeScene(event,"ChangeEmailMenu.fxml");
	}
	@FXML public void click_changeUsername(ActionEvent event) throws IOException {
		//open change Username menu
		changeScene(event,"ChangeUsernameMenu.fxml");
	}
	@FXML public void click_changePass(ActionEvent event) throws IOException {
		//open change Pass menu
		changeScene(event,"ChangePasswordMenu.fxml");
	}
	@FXML public void click_changeDuration(ActionEvent event) throws IOException {
		//open change Duration menu
	}
}
