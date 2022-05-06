package application;

import java.io.IOException;

import GeneralSettings.Settings;
import PasswordGenerator.PasswordGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class GeneratePassController extends AppUI{

	@FXML
	private TextField lower;
	@FXML
	private TextField upper;
	@FXML
	private TextField intValue;
	@FXML
	private TextField special;
	@FXML
	private Label newPass;
	@FXML
	private Label passLength;
	
	public static String previousScene = Settings.EditingAccountScene; // this hold the previous scene
	
	/**
	 * This password get user back to previous page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_cancel(ActionEvent event) throws IOException {
		//back to app info
		changeScene(event, previousScene);
	}
	
	/**
	 * This method get user back to previous page while copy new generated password to clipboard
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void click_use(ActionEvent event) throws IOException
	{
		//save new pass and update the database
		Settings.tempAccount.setAppPass(newPass.getText());
		
		// Copy new pass to clip board
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(newPass.getText());
		clipboard.setContent(content);
		
		alretMessege("Copied this password to clipboard: " + newPass.getText());
		
		// go back to previous scene
		changeScene(event, previousScene);
	}
	
	/**
	 * This password get user input and generate appropriate password
	 * @param event
	 * @throws IOException
	 */
	public void click_generatePass(ActionEvent event) throws IOException {
		//generate new pass 
		String lowerString = "0" + lower.getText().replaceAll("[^0-9]", "").trim();
		String upperString = "0" + upper.getText().replaceAll("[^0-9]", "").trim();
		String specialString = "0" + special.getText().replaceAll("[^0-9]", "").trim();
		String digitString = "0" + intValue.getText().replaceAll("[^0-9]", "").trim();
		

		int numLower = Integer.parseInt(lowerString);
		int numUpper = Integer.parseInt(upperString);
		int numSpecial = Integer.parseInt(specialString);
		int numDigit = Integer.parseInt(digitString);
		
		int passwordLength = numLower + numUpper + numSpecial + numDigit;
		String password = PasswordGenerator.generatePassword(numLower, numUpper, numSpecial, numDigit);
		
		passLength.setText(String.valueOf(passwordLength));
		newPass.setText(password);
		
	}
}
