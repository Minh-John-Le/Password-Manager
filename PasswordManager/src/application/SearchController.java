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
	
	
	/**
	 * This method fill in the page with all searchAccount
	 */
	@FXML
	public void initialize() 
	{
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

	/**
	 * This method get the password from selected account 
	 * Then copy this password to clip board
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void clickCopy(ActionEvent event) throws IOException 
	{
		Account account = table.getSelectionModel().getSelectedItem();
		if (account == null)
		{
			return;
		}

		String myPassword = account.getAppPass();
		
		// save password to clip board
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString(myPassword);
		clipboard.setContent(content);
		
		// annouce user password have been save to clipboard
		alretMessege(text + myPassword);
		
	}
	
	/**
	 * This method return user back to main menu
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void clickMain(ActionEvent event) throws IOException 
	{
		// back to main menu
		changeScene(event, Settings.MainScene);
	}
	
	/**
	 * This method return user to Log In page
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void clickLogout(ActionEvent event) throws IOException 
	{
		//ask for confirmation and sign out
		//update database
		if(alretConfirmation("Do you want to log out?")) 
		{
			changeScene(event, Settings.LoginScene);
		}
	}
	
	/**
	 * This method get user to editing account page for selected accounts
	 * @param event
	 * @throws IOException
	 */
	@FXML public void clickEdit(ActionEvent event) throws IOException 
	{
		// get selected account
		Account account = table.getSelectionModel().getSelectedItem();
		if (account == null)
		{
			return;
		}
		// give information to edit Info Controller
		EditInfoController.previousScene = Settings.SearchScene;
		Settings.selectedAccount = account;
		
		// Deep copy all information from the selected account to temp account
		// In order to display information in Editing account page
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
	
	/** 
	 * This method update the table with the result from searching
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void clickSearch(ActionEvent event) throws IOException 
	{
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
