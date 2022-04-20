package application;

import java.io.IOException;
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
			String username = getUsername().getText();
			String password = getPassword().getText();
			
			//logic for check user/password
			if(!username.isEmpty() && !password.isEmpty()) {
				if(SQLiteDatabase.isUserExist(username)) {
					
					if(SQLiteDatabase.searchUser(username).getString(3).equals(password))
					{
						changeScene(event,fxml1);
					}
					else {
						alretMessege("Wrong Password. Try Again!!!");
					}	
				}
				else {
					alretMessege("UserName is wrong or not exits!!!");
				}
				
				
				//check account if there is expired info, if yes first show warning
				//if(expired) then show warning ,else show main
				
				//clear the user pass textfield after login
				//the reason is when we get back to this scene, the textfiled always 
				//should be ready for enter new entry

				//setup the main menu
				
				//set the user name in main menu if login is successful

				
				
			}else {
				alretMessege("Please Enter UserName and Password!!!");
				
			}
			
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