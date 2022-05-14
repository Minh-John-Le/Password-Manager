package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import DAO.AddAccountDAO;
import GeneralSettings.Settings;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAppController extends AppUI{

	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField email;
	@FXML
	private TextField duration;
	@FXML
	private TextField appName;
	
	@FXML
	private void initialize()
	{
		
		username.setText(Settings.tempAccount.getUserName());
		pass.setText(Settings.tempAccount.getAppPass());
		email.setText(Settings.tempAccount.getEmail());
		duration.setText(Settings.tempAccount.getDuration());
		appName.setText(Settings.tempAccount.getAppName());
	}

	/**
	 * This method Verified information of the new application
	 * Then add it into the user account storage
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_submit(ActionEvent event) throws IOException 
	{
		String appNameString = appName.getText().trim();
		String accountUsernameString = username.getText().trim();
		String accountPassString = pass.getText();
		String emailString = email.getText().trim();
		String durationString = duration.getText().replaceAll("[^0-9]", "").trim();
		
		if (appNameString.equals(""))
		{
			
			alretMessege("application name cannot be empty");
			return;				
		}
		
				
		if (durationString.length() < duration.getText().length())
		{
			alretMessege("Duration cannot contain any character or be negative!");
			return;
		}
		
		if (durationString.equals(""))
		{
			alretMessege("Duration  cannot be empty!");
			return;	
		}
		
		if (accountUsernameString.equals(""))
		{
			alretMessege("Username cannot be empty");
			return;
		}
		
		
		if (accountPassString.equals(""))
		{
			alretMessege("Password cannot be empty");
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
		
		
		
		boolean isCreatedAccount = AddAccountDAO.addNewAccount(Settings.currentUser.getUserID(), appNameString, accountUsernameString,
				accountPassString, emailString, newCreatedDay, newExpiredDay, String.valueOf(newDuration));
		
		if(isCreatedAccount)
		{
			alretMessege("Sucessful create new account!");
			// set tempt account
			Settings.tempAccount = new Account();
			changeScene(event, Settings.MainScene);
			return;
		}
		
		alretMessege("Please add a different account! This account has already added!");
	}
	
	/**
	 * This method get user go back to the previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_cancel(ActionEvent event) throws IOException {
		Settings.tempAccount = new Account();
		changeScene(event, Settings.MainScene);
	}
	
	/**
	 * This method get user to password generator
	 * after generate the password, the generated password will auto-filled in password location
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_generatePass(ActionEvent event) throws IOException
	{
		Settings.tempAccount.setAppName(appName.getText());
		Settings.tempAccount.setAppPass(pass.getText());
		Settings.tempAccount.setEmail(email.getText());
		Settings.tempAccount.setUserName(username.getText());
		Settings.tempAccount.setDuration(duration.getText());
		GeneratePassController.previousScene = Settings.AddAccountScene;
		changeScene(event, Settings.PassGeneratorScene);
	}
	
}