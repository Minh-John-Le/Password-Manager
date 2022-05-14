package application;

import java.io.IOException;

import DAO.DeleteUserDAO;
import GeneralSettings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SettingController extends AppUI{
	//warning text for delete master profile
	private final String warningText = "Do you want to delete your master account?"; 
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextArea quest;
	@FXML
	private TextField ans;
	
	/**
	 * This method fill in user information to appropriate text field
	 */
	@FXML 
	public void initialize() 
	{
		String usernameString = Settings.currentUser.getUserName();
		String passwordString = Settings.currentUser.getUserPass();
		String questString = Settings.currentUser.getSecQuestion();
		String ansString = Settings.currentUser.getAnswer();
		
		username.setText(usernameString);
		pass.setText(passwordString);
		
		quest.setText(questString);
		//this is so important to be in code. it let the question to fit properly in the textfield
		quest.setWrapText(true);
		ans.setText(ansString);
	}
	
	/**
	 * This method get user back to previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_back(ActionEvent event) throws IOException 
	{
		// back to main menu
		changeScene(event, Settings.MainScene);
	}

	/**
	 * This method propmt user to change password page
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_changePass(ActionEvent event) throws IOException 
	{
		// back to main menu
		ChangeLoginPassController.previousScene = Settings.UserProfileScene;
		changeScene(event, Settings.ResetLogInPasswordScene);
	}
	
	/**
	 * This method get user to change security question page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_changeQuest(ActionEvent event) throws IOException 
	{
		// back to main menu
		changeScene(event, Settings.ChangeQuestionScene);
	}
	
	/**
	 * This method get user to change security answer page
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_changeAns(ActionEvent event) throws IOException 
	{
		// back to main menu
		changeScene(event, Settings.ChangeAnswerScene);
	}
	
	/**
	 * This method allow user to delete master account profile
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_delete(ActionEvent event) throws IOException 
	{
		// back to main menu
		if(alretConfirmation(warningText)) 
		{
			DeleteUserDAO.deleteUser(Settings.currentUser.getUserID());
			changeScene(event, Settings.LoginScene);
		}
	}
}