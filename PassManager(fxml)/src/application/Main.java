/**
 * Main class is the starter of the program, 
 * it implements some generic functions that other controller use through inheritance
 */

package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main   extends Application{
	private final String title = "Password Management System";
	private final String fxml = "LoginMenu.fxml";
	private AppUI newLogin;
	 
    //this is the starting point of the program
	@Override
	public void start(Stage primaryStage) throws IOException {
		newLogin = new AppUI();
		newLogin.setStage(fxml,title);
	}
	public static void main(String[] args) {
			launch(args);
	}
}