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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

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
	@FXML private TableColumn<Account , String> dateExpired;
	
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
		dateExpired.setCellValueFactory(new PropertyValueFactory<Account ,String> ("dateExpired"));
		table.setItems(searchAccounts);
		table.getSelectionModel().selectFirst();
	}

	@FXML public void clickCopy(ActionEvent event) throws IOException {
		// copy
		
		// Get password
		int index = table.getSelectionModel().getSelectedIndex();
		if (index < 0)
		{
			return;
		}
		String myPassword = searchAccountList.get(index).getAppPass();
		
		// save password to clipboard
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(myPassword);
		clipboard.setContent(content);
		
		// annouce user password have been save to clipboard
		alretMessege(text + myPassword);
		
	}
	@FXML public void clickMain(ActionEvent event) throws IOException {
		// back to main menu
		changeScene(event,"MainMenu.fxml");
	}
	
	
	@FXML public void clickLogout(ActionEvent event) throws IOException {
		//ask for confirmation and sign out
		//update database
		if(alretConfirmation("Do you want to Quit?")) 
		{
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
		dateExpired.setCellValueFactory(new PropertyValueFactory<Account ,String> ("dateExpired"));
		table.setItems(allAccountlist);
		table.getSelectionModel().selectFirst();

	}

}
