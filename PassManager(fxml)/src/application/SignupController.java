package application;

import java.io.IOException;
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
		//logic for saving new user info in database
		SQLiteDatabase.createNewUser(username.getText(),pass.getText(),quest.getText(),ans.getText());
		changeScene(event,fxml1);
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}
	public void click_generatePass(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}

}