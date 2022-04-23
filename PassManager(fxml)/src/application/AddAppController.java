package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DAO.AddAccountDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAppController extends AppUI{
	private final String fxml1 = "GeneratePassMenu.fxml";
	private final String fxml2 = "MainMenu.fxml";
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField repeatedPass;
	@FXML
	private TextField email;
	@FXML
	private TextField duration;
	@FXML
	private TextField appName;
	

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to main menu after deleting the data
	public void click_submit(ActionEvent event) throws IOException 
	{
		String appNameString = appName.getText().trim();
		String accountUsernameString = username.getText().trim();
		String accountPassString = pass.getText();
		String repeatedPassString = repeatedPass.getText().trim();
		String emailString = email.getText().trim();
		String durationString = duration.getText().replaceAll("[^0-9]", "").trim();
		
		if (accountUsernameString.equals(""))
		{
			alretMessege("username cannot be empty");
			return;
		}
		
		
		if (!accountPassString.equals(repeatedPassString))
		{		
			alretMessege("password and repeated password not match!");
			return;
		}
		
		if (accountPassString.equals(""))
		{
			alretMessege("password and repeated password cannot be empty");
			return;
		}
		
	
		//DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		
		// calculate current day, duration and expired day
		LocalDate today = LocalDate.now();
		
		int newDuration = Settings.defaultDuration;
		
		// if user have refer duration set new duration
		if(!durationString.equals(""))
		{
			newDuration = Integer.parseInt(durationString);
		}
		
		
		LocalDate future = LocalDate.now().plusDays(newDuration);
		
		String newCreatedDay = today.toString().replace("-", "/");
		String newExpiredDay = future.toString().replace("-", "/");
		
		
		
		boolean isCreatedAccount = AddAccountDAO.addNewAccount(Settings.currentUserID, appNameString, accountUsernameString,
				accountPassString, emailString, newCreatedDay, newExpiredDay, String.valueOf(newDuration));
		
		if(isCreatedAccount)
		{
			alretMessege("Sucessful create new account!");
			changeScene(event,fxml2);
			return;
		}
		
		alretMessege("Please add a different account! This account has already added!");
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}
	@FXML
	//it returns back app info menu
	public void click_generatePass(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}
	
}