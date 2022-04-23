package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import DAO.LoginDAO;

public class LoginController extends AppUI {
	//title of the menu
	private final String fxml1 = "MainMenu.fxml";
	private final String fxml2 = "SignUpMenu.fxml";
	public static User user;
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
		String username = getUsername().getText();
		String password = getPassword().getText();
		try {
			user = LoginDAO.getUser(username);
			if(username.isEmpty() || password.isEmpty()) {
				alretMessege("Please Enter UserName and Password!!!");
				return;
				
			}
			
			if(user == null){
				alretMessege("UserName is wrong or not exits!!!");
				return;
				}
			
			if(user.getUserPass().equals(password)) {
				
				changeScene(event,fxml1);
			}
			else alretMessege("Wrong Password. Try Again !!!");
				
	
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