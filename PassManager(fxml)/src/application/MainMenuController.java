package application;
	

import java.io.IOException;
import java.util.ArrayList;

import DAO.SearchAccountDAO;
import GeneralSettings.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainMenuController extends AppUI {
	private final String text = "The password is copied.?";
	private final String messege = "The Record is Copied?";
	private final String fxml1 = "AppInfoMenu.fxml";
	private final String fxml2 = "SettingMenu.fxml";
	private final String fxml3 = "LoginMenu.fxml";
	private final String fxml4 = "SearchMenu.fxml";
	private final String fxml5 = "ExpiredPassMenu.fxml";
	private final String fxml6 = "AddAppMenu.fxml";
	private final String fxml7 = "LogoutMenu.fxml";
	
	@FXML
	private Label userLabel;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> appPass;
	
	// beans Object
	
	public static ArrayList<Account> allAccounts = null;
	

	@FXML
	public void initialize() {
	    //Initializing the table
		allAccounts = SearchAccountDAO.getAccount(Settings.currentUserID, "", "");
		ObservableList<Account> accList = FXCollections.observableArrayList(allAccounts);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(accList);
		table.getSelectionModel().selectFirst();
	}
	@FXML public void clickEdit(ActionEvent event) throws IOException{
		changeScene(event,fxml1);

	}
	@FXML public void clickCopy(ActionEvent event){
		alretMessege(text);
	}
	@FXML public void clickSetting(ActionEvent event) throws IOException{
		changeScene(event,fxml2);
	}
	@FXML public void clickLogout(ActionEvent event) throws IOException{
		if(alretConfirmation(text))
			changeScene(event,fxml3);
	}
	@FXML public void clickSearch(ActionEvent event) throws IOException
	{
		changeScene(event,fxml4);
	}
	@FXML public void clickExpired(ActionEvent event) throws IOException{
		changeScene(event,fxml5);
	}
	@FXML public void clickAdd(ActionEvent event) throws IOException{
		changeScene(event,fxml6);
	}
	@FXML public void onCloseRequest(ActionEvent event) throws IOException{
		changeScene(event,fxml7);
	}
	

	
	
}