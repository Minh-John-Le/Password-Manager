package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AskUsernameController extends AppUI {
	private final String fxml1 = "ResetPassQuestionMenu.fxml";
	private final String fxml2 = "LoginMenu.fxml";
	@FXML
	private TextField user;
	@FXML



	public void click_submit(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml1);
	}
	
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}
}
