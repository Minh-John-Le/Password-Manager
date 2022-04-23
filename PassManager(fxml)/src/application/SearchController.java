package application;

import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;

import DAO.SearchAccountDAO;
import GeneralSettings.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchController extends AppUI{
	private final String text = "The password is copied.";
	@FXML
	private Label user;
	@FXML
	private TextField Application;
	@FXML
	private TextField Username;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> appPass;
	
	// beans Object
	public static ArrayList<Account> searchAccountList = new ArrayList<Account>();
	
	
	//this function set the user name that successfully logs in 
	//and sends it to main menu. so the main menu would be able to 
	//show account info associated with this user name
	@FXML
	public void initialize() {
		ObservableList<Account> searchAccounts = FXCollections.observableArrayList(searchAccountList);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(searchAccounts);
		table.getSelectionModel().selectFirst();
	}

	@FXML public void clickCopy(ActionEvent event) throws IOException {
		// copy
		alretMessege(text);
		changeScene(event,"MainMenu.fxml");
	}
	@FXML public void clickMain(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"MainMenu.fxml");
	}
	@FXML public void clickLogout(ActionEvent event) throws IOException {
		//ask for confirmation and sign out
		//update database
		if(alretConfirmation(text)) {
		changeScene(event,"LoginMenu.fxml");
		}
	}
	@FXML public void clickEdit(ActionEvent event) throws IOException {
		//update the table
		changeScene(event,"EditInfoMenu.fxml");
	}
	@FXML public void clickSearch(ActionEvent event) throws IOException {
		String application = Application.getText();
		String username = Username.getText();
		
		ArrayList<Account> accountList = 
				SearchAccountDAO.getAccount(Settings.currentUserID,application,username);
		//update the table
		SearchController.searchAccountList = accountList;
		

		ObservableList<Account> allAccountlist = FXCollections.observableArrayList(searchAccountList);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(allAccountlist);
		table.getSelectionModel().selectFirst();

	}

}
