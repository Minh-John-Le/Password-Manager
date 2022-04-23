package application;

import java.io.IOException;

import DAO.SQLiteDatabase;
import DAO.SignUpDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignupController extends AppUI{
	private final String fxml1= "LoginMenu.fxml";
	private final String fxml2= "GeneratePassMenu.fxml";
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

			
			
	
			String newUsername = username.getText();
			String newPass = pass.getText();
			String newRepeatedPass = repeatedPass.getText();
			String newQuest = quest.getText();
			String newAns = ans.getText();
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
					changeScene(event,fxml1);
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
	public void click_cancel(ActionEvent event) throws IOException {
		alretMessege("See you again!!!");
		changeScene(event,fxml1);
	}
	public void click_generatePass(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}

}