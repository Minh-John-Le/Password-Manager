package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
public class ChangePasswordController extends AppUI{
	private final String fxml1 = "AppInfoMenu.fxml";
	private final String fxml2 ="PassGeneratorMenu.fxml";
	@FXML
	private TextField oldPass;
	@FXML
	private TextField newPass;
	@FXML
	private TextField repeatePass;

	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		oldPass.setText("John_Smith");
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void clickSubmit(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml1);
	}
	@FXML
	//it returns back app info menu
	public void clickCancel(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}		
	@FXML
	//it returns back app info menu
	public void clickGenerator(ActionEvent event) throws IOException {
		changeScene(event,fxml2);
	}


}