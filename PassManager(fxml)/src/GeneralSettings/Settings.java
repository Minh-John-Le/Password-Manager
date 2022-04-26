package GeneralSettings;
import application.Account;
import application.User;

public class Settings {
	// DAO Settings
	public static final String jdbcUrl = "jdbc:sqlite:applicationDb.db";
	public static final String usernameTable = "userInfoTable";
	public static final String accountTable = "accountInfoTable";
	
	
	// Account setting
	public static final int defaultDuration = 30;
	
	// Bean can
	public static Account selectedAccount = null;
	public static User currentUser = null;
	public static Account tempAccount = new Account(-1,-1, "","","","","","","");
	
	
	//===============================================================================================================
	//Scene Setting
	// Main Scene
	public static final String MainScene = "/View/MainMenu.fxml";
	
	// Expire Pass Scene
	public static final String ExpiredPasswordScene = "/View/ExpiredPassMenu.fxml";
	
	// Add account Scene
	public static final String AddAccountScene = "/View/AddAppMenu.fxml";	
	
	// Editing Account Scene
	public static final String EditingAccountScene = "/View/EditInfoMenu.fxml";
	
	// Search Scene
	public static final String SearchScene = "/View/SearchMenu.fxml";
	
	// Log In Scene 
	public static final String LoginScene = "/View/LoginMenu.fxml";
	public static final String SignUpScene ="/View/SignUpMenu.fxml";
	public static final String AskUsernameScene = "/View/AskUsernameMenu.fxml"; // forgot pass
	

	
	
	// Password generator Scene
	public static final String PassGeneratorScene = "/View/GeneratePassMenu.fxml";	
	
	// User profile Scene
	public static final String UserProfileScene = "/View/SettingMenu.fxml";	
	
	// User Scene Editing Related
	public static final String ResetPassQuestionScene = "/View/ResetPassQuestionMenu.fxml";
	public static final String ResetLogInPasswordScene = "/View/ChangeLoginPassMenu.fxml";
	public static final String ChangeQuestionScene = "/View/ChangeQuestionMenu.fxml";
	public static final String ChangeAnswerScene = "/View/ChangeAnswerMenu.fxml";
}
