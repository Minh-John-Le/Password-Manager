package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class ChangeUsernameController extends AppUI{

	private final String fxml = "AppInfoMenu.fxml";
	@FXML
	private TextField oldUsername;
	@FXML
	private TextField newUsername;

	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		//we have to show the old user name in screen
		oldUsername.setText("John_Smith");
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_submit(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml);
	}

	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml);
	}	
}