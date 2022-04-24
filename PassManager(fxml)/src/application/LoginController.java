package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DAO.LoginDAO;
import DAO.SearchAccountDAO;
import DAO.SignUpDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController extends AppUI {
	//title of the menu
	private final String fxml1 = "MainMenu.fxml";
	private final String fxml2 = "SignUpMenu.fxml";
	private final String fxml3 = "AskUsernameMenu.fxml";
	
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
		
			
		String username = getUsername().getText().trim();
		String password = getPassword().getText().trim();
		try {
			User user = LoginDAO.getUser(username);
			
			// Empty pass and username
			if (username.isEmpty() || password.isEmpty()) 
			{
				alretMessege("Please Enter UserName and Password!!!");
				return;			
			}
			
			// Cannot find the username in system
			if (user == null)
			{
				alretMessege("UserName is wrong or not exits!!!");
				return;
			}
			
			// Success log in
			if(user.getUserPass().equals(password))
			{
				Settings.currentUser = user;
				if(!hasExpiredPassword(user))
				{
					changeScene(event,fxml1);
				}
				else
				{
					changeScene(event,Settings.expiredPasswordScene);
				}
				return;
			}
			
			alretMessege("Wrong Password. Try Again !!!");
				
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	//load sign up menu
	public void click_signup(ActionEvent event) throws IOException {
		try 
		{	
			changeScene(event,fxml2);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@FXML
	//calls forget password menu
	public void click_forgetPass(ActionEvent event) throws IOException {

	changeScene(event,fxml3);

	}


	//clear the user and pass text fields on screen, so user can enter the new one
	public void clearUserPass() {
		
		if(!isTextFieldCleared(getUsername().getText()))
		username.clear();
		if(!isTextFieldCleared(getPassword().getText()))
		password.clear();
	}

/**
 * this method check if an account have expired password
 * @param user
 * @return
 */
	
	private boolean hasExpiredPassword(User user)
	{
		ArrayList<Account> allAccount = LoginDAO.getAccount(user.getUserID(), "" , "");
		LocalDate today = LocalDate.now();
		
		for (Account account : allAccount)
		{

			String expDateString = account.getDateExpired();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
			LocalDate expDate = LocalDate.parse(expDateString,dateFormat);
			
			if (expDate.compareTo(today) <= 0)
			{
				return true;
			}
		}

		return false;
	}
}