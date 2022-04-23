package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResetPassQuestionController extends AppUI  {
	private final String fxml1 = "LoginMenu.fxml";
	private final String fxml2 = "ChangeLoginPassMenu.fxml";
	@FXML
	private TextField user;
	@FXML
	private TextArea quest;
	@FXML
	private  TextField ans;
	@FXML

	public void click_submit(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml2);
	}
	
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}

}
