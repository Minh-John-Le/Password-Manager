package application;

import java.io.IOException;

import DAO.SQLiteDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController extends AppUI {
	//title of the menu
	private final String fxml1 = "MainMenu.fxml";
	private final String fxml2 = "SignUpMenu.fxml";
	
	@FXML
	private PasswordField password;
	@FXML
	private TextField username;
	
	public PasswordField getPassword() {
		return password;
	}

	public TextField getUsername() {
		return username;
	}

	
	@FXML
	public void initialize() throws IOException {
		
	}

	@FXML
	//check the password and then call main menu if successful
	public void click_login(ActionEvent event) throws IOException {
		try {

				

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	//load sign up menu
	public void click_signup(ActionEvent event) throws IOException {
		try {
			changeScene(event,fxml2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	//calls forget password menu
	public void click_forgetPass(ActionEvent event) throws IOException {

	}
	
	//check user password
	//this code is just for testing propose. should be edited later on
	//password is PasswordFiled type which shows password in ******.
	//other logic related to check the password should be placed in this function
	public Boolean checkUserPassword() {
		//if(password.getText().compareTo("")==0 || username.getText().compareTo("nima")==0)
			return true;
		//return false;

	}


	//clear the user and pass text fields on screen, so user can enter the new one
	public void clearUserPass() {
		
		if(!isTextFieldCleared(getUsername().getText()))
		username.clear();
		if(!isTextFieldCleared(getPassword().getText()))
		password.clear();
	}

}