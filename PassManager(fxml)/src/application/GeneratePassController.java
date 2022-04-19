package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GeneratePassController extends AppUI{
	private final String fxml = "AppInfoMenu.fxml";
	@FXML
	private TextField lower;
	@FXML
	private TextField upper;
	@FXML
	private TextField intValue;
	@FXML
	private TextField special;
	@FXML
	private TextField newPass;
	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_cancel(ActionEvent event) throws IOException {
		//back to app info
		changeScene(event,fxml);
	}
	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_use(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml);
	}
	public void click_generatePass(ActionEvent event) throws IOException {
		//generate new pass 
		
	}
}
