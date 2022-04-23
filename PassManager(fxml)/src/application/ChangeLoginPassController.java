package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeLoginPassController extends AppUI {
	private final String fxml = "LoginMenu.fxml";
	@FXML
	private TextField oldPass;
	@FXML
	private TextField newPass;
	@FXML
	private TextField repeatePass;

	@FXML 
	public void initialize() {
		oldPass.setText("John_Smith");
	}

	@FXML
	public void clickSubmit(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml);
	}
	@FXML
	public void clickCancel(ActionEvent event) throws IOException {
		changeScene(event,fxml);
	}		

}
