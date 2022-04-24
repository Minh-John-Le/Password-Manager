package GeneralSettings;
import application.Account;
import application.User;

public class Settings {
	public static final String jdbcUrl = "jdbc:sqlite:applicationDb.db";
	public static final String usernameTable = "userInfoTable";
	public static final String accountTable = "accountInfoTable";
	
	
	// Account setting
	public static final int defaultDuration = 30;
	
	// Bean can
	public static Account selectedAccount = null;
	public static User currentUser = null;
	
	
	
	// Scene Setting
	public static final String ExpiredPasswordScene = "ExpiredPassMenu.fxml";
	public static final String MainScene = "MainMenu.fxml";
	public static final String SignUpScene ="SignUpMenu.fxml";
	public static final String AskUsernameScene = "AskUsernameMenu.fxml";
	public static final String EditingAccountScene = "EditInfoMenu.fxml";
	public static final String SearchScene = "SearchMenu.fxml";
}
