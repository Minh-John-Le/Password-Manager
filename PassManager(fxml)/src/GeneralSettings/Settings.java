package GeneralSettings;
import application.User;

public class Settings {
	public static final String jdbcUrl = "jdbc:sqlite:applicationDb.db";
	public static final String usernameTable = "userInfoTable";
	public static final String accountTable = "accountInfoTable";
	
	
	// Account setting
	public static final int defaultDuration = 30;
	
	
	// User setting
	public static User currentUser = null;
	
	
	public static final String expiredPasswordScene = "ExpiredPassMenu.fxml";
	
}
