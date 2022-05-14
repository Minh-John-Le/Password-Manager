package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DAO.ExpiredPasswordDAO;
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

public class ExpiredPassController extends AppUI{
	private final String logOutText = "Do you want to log out?";
	@FXML
	private Label userLabel;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> expiredDate;
	

	@FXML
	public void initialize() 
	{
		// set user name
		userLabel.setText(Settings.currentUser.getUserName());
		
		// Get expire password
		ArrayList<Account> allAccount = ExpiredPasswordDAO.getAccount(Settings.currentUser.getUserID(), "", "");
		ArrayList<Account> expAccountList = getExpiredAccount(allAccount);
		
		// Set up table
		ObservableList<Account> accList = FXCollections.observableArrayList(expAccountList);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		expiredDate.setCellValueFactory(new PropertyValueFactory<Account ,String> ("dateExpired"));
		table.setItems(accList);
		table.getSelectionModel().selectFirst();
	}
	
	/**
	 * This method prompt user to editing page 
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void click_Edit(ActionEvent event) throws IOException
	{
		Account account = table.getSelectionModel().getSelectedItem();
		if (account == null)
		{
			return;
		}
		// give info mation to edit Info Controller
		EditInfoController.previousScene = Settings.ExpiredPasswordScene;
		Settings.selectedAccount = account;

		// deep copy selected account info to temporary account
		// In order to display in account info page
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
	 * This method allow user to get back to log in page
	 * @param event
	 */
	@FXML 
	public void click_Logout(ActionEvent event)
	{
		try {
			if(alretConfirmation(logOutText))
			{
				changeScene(event, Settings.LoginScene);
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method get user back to main page
	 * @param event
	 */
	@FXML 
	public void click_Main(ActionEvent event)
	{
		try {
			
			changeScene(event, Settings.MainScene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method get user to Master profile page
	 * @param event
	 */
	@FXML 
	public void click_Setting(ActionEvent event)
	{
		try {
			
			changeScene(event, Settings.UserProfileScene);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method return the list of account that get expired
	 */
	private ArrayList<Account> getExpiredAccount(ArrayList<Account> accountList)
	{
		ArrayList<Account> expAccountList = new ArrayList<Account>();
		LocalDate today = LocalDate.now();
		for (Account account : accountList)
		{
			String expDateString = account.getDateExpired();
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
			LocalDate expDate = LocalDate.parse(expDateString,dateFormat);
			
			if (expDate.compareTo(today) <= 0)
			{
				expAccountList.add(account);
			}
		}
		return expAccountList;
	}
}
