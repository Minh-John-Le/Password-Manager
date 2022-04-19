package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAppController extends AppUI{
	private final String fxml1 = "GeneratePassMenu.fxml";
	private final String fxml2 = "MainMenu.fxml";
	@FXML
	private TextField username;
	@FXML
	private TextField pass;
	@FXML
	private TextField repeatedPass;
	@FXML
	private TextField email;
	@FXML
	private TextField duration;
	@FXML
	private TextField appName;
	

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to main menu after deleting the data
	public void click_submit(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}
	@FXML
	//it returns back app info menu
	public void click_generatePass(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}
	
}