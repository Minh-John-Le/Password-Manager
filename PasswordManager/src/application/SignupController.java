package application;

import java.io.IOException;

import DAO.SignUpDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import testUnit.SQLiteDatabase;

public class SignupController extends AppUI{

	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField repeatedPass;
	@FXML
	private TextField quest;
	@FXML
	private TextField ans;
	
	/**
	 * This method verified user input 
	 * Then create new user account and record it in database
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_submit(ActionEvent event) throws IOException {
		try 
		{

			String newUsername = username.getText().trim();
			String newPass = pass.getText().trim();
			String newRepeatedPass = repeatedPass.getText().trim();
			String newQuest = quest.getText().trim();
			String newAns = ans.getText().trim();
			boolean canSignUp = false;
			
			if (newUsername.equals(""))
			{
				alretMessege("Username cannot be empty!");		
				return;
			}
			
			if (newPass.equals(""))
			{
				alretMessege("Password cannot be empty!");		
				return;
			}
			
			if (newQuest.equals(""))
			{
				alretMessege("Question cannot be empty!");		
				return;
			}
			
			if (newAns.equals(""))
			{
				alretMessege("Answer cannot be empty!");		
				return;
			}
			
			if(newPass.equals(newRepeatedPass)){
				canSignUp = SignUpDAO.createNewUser(newUsername, newRepeatedPass, newQuest, newAns);
			
				if(!canSignUp)
				{
					alretMessege("Username is already exist!");
				}
				else
				{
					alretMessege("Sign Up Succcessful!!");		
					changeScene(event, Settings.LoginScene);
				}
				
			}
			else 
			{
				alretMessege("Password and RepeatedPassword don't match");
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
		
	/**
	 * This method get user back to log in scene
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_cancel(ActionEvent event) throws IOException 
	{
		changeScene(event, Settings.LoginScene);
	}
	

}