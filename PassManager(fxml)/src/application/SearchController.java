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
	private final String text = "Sucess copy this pasword to clipboard: ";
	@FXML
	private Label userLabel;
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
		// Set user name
		userLabel.setText(Settings.currentUser.getUserName());
		//get all account
		searchAccountList = SearchAccountDAO.getAccount(Settings.currentUser.getUserID(), "", "");
		
		// Fill in table
		ObservableList<Account> searchAccounts = FXCollections.observableArrayList(searchAccountList);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		dateExpired.setCellValueFactory(new PropertyValueFactory<Account ,String> ("dateExpired"));
		table.setItems(searchAccounts);
		table.getSelectionModel().selectFirst();
	}

	@FXML public void clickCopy(ActionEvent event) throws IOException {
		Account account = table.getSelectionModel().getSelectedItem();
		if (account == null)
		{
			return;
		}

		String myPassword = account.getAppPass();
		
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
		changeScene(event, Settings.MainScene);
	}
	
	
	@FXML public void clickLogout(ActionEvent event) throws IOException {
		//ask for confirmation and sign out
		//update database
		if(alretConfirmation("Do you want to log out?")) 
		{
			changeScene(event, Settings.LoginScene);
		}
	}
	
	
	@FXML public void clickEdit(ActionEvent event) throws IOException 
	{
		// get selected account
		Account account = table.getSelectionModel().getSelectedItem();
		if (account == null)
		{
			return;
		}
		// give info mation to edit Info Controller
		EditInfoController.previousScene = Settings.SearchScene;
		Settings.selectedAccount = account;
		
		Account tempAccount = Settings.tempAccount;
		tempAccount.setAppName(account.getAppName());
		tempAccount.setUserName(account.getUserName());
		tempAccount.setEmail(account.getEmail());
		tempAccount.setAppPass(account.getAppPass());
		tempAccount.setDuration(account.getDuration());
		tempAccount.setDateCreated(account.getDateCreated());
		tempAccount.setDateExpired(account.getDateExpired());
		
		changeScene(event, Settings.EditingAccountScene);
	}
	
	
	@FXML public void clickSearch(ActionEvent event) throws IOException {
		String application = Application.getText();
		String username = Username.getText();
		
		ArrayList<Account> accountList = 
				SearchAccountDAO.getAccount(Settings.currentUser.getUserID(),application,username);
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
