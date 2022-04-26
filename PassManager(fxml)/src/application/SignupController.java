package application;

import java.io.IOException;

import DAO.SQLiteDatabase;
import DAO.SignUpDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
	
	
	@FXML
	//it returns back app info menu
	public void click_submit(ActionEvent event) throws IOException {
		try 
		{

			String newUsername = username.getText().trim();
			String newPass = pass.getText().trim();
			String newRepeatedPass = repeatedPass.getText().trim();
			String newQuest = quest.getText().trim();
			String newAns = ans.getText().trim();
			boolean canSignUp = false;
		
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
		
	
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException 
	{
		changeScene(event, Settings.LoginScene);
	}
	

}