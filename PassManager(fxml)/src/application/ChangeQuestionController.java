package application;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
public class ChangeQuestionController extends AppUI {
	
	private final String fxml1 = "AppInfoMenu.fxml";
	@FXML
	private TextArea oldQuest;
	@FXML
	private TextField newQuest;
	@FXML 
	//initializer fill out the menu with the app information
	//here is a sample showing the UI works
	//The data comes from The database,it should be connected to database
	public void initialize() {
		oldQuest.setText("what is your favorate color");
		newQuest.setText("what is your favorate car");
	}

	@FXML
	//provide logic behind deleting data and updating the database
	//it returns to app info
	public void click_submit(ActionEvent event) throws IOException {
		//save new pass and update the database
		changeScene(event,fxml1);
	}
	@FXML
	//it returns back app info menu
	public void click_cancel(ActionEvent event) throws IOException {
		changeScene(event,fxml1);
	}		
}
