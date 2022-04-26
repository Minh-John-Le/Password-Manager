/**
 * Main class is the starter of the program, 
 * it implements some generic functions that other controller use through inheritance
 */

package application;
	
import java.io.IOException;

import DAO.InitializeDAO;
import GeneralSettings.Settings;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main   extends Application{
	private final String title = "Password Management System";
	private AppUI newLogin;
	 
    //this is the starting point of the program
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		newLogin = new AppUI();
		newLogin.setStage(Settings.LoginScene,title);
	}
	public static void main(String[] args) {
			InitializeDAO.initializeDatabase();
			launch(args);
	}
}