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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;


public class MainMenuController extends AppUI {
	private final String text = "Sucess copy this pasword to clipboard: ";
	private final String fxml2 = "SettingMenu.fxml";
	private final String fxml3 = "LoginMenu.fxml";

	private final String fxml5 = "ExpiredPassMenu.fxml";
	private final String fxml6 = "AddAppMenu.fxml";
	private final String fxml7 = "LogoutMenu.fxml";
	
	@FXML
	private Label userLabel;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> expiredDate;
	
	// beans Object
	
	public static ArrayList<Account> allAccounts = new ArrayList<Account>();
	

	@FXML
	public void initialize() {
		// Fill in username field
		userLabel.setText(Settings.currentUser.getUserName());
		
	    //Initializing the table
		allAccounts = SearchAccountDAO.getAccount(Settings.currentUser.getUserID(), "", "");
		ObservableList<Account> accList = FXCollections.observableArrayList(allAccounts);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		expiredDate.setCellValueFactory(new PropertyValueFactory<Account ,String> ("dateExpired"));
		table.setItems(accList);
		table.getSelectionModel().selectFirst();
	}
	
	
	/**
	 * This method for getting to editing scene for selected account
	 * @param event
	 * @throws IOException
	 */
	@FXML 
	public void clickEdit(ActionEvent event) throws IOException
	{
		// get selected account
		Account account = table.getSelectionModel().getSelectedItem();
		if (account == null)
		{
			return;
		}
		// give info mation to edit Info Controller
		EditInfoController.previousScene = Settings.MainScene;
		Settings.selectedAccount = account;
		
		changeScene(event, Settings.EditingAccountScene);
	}
	
	
	
	
	/**
	 * This method copy the password of selected account to clipboard
	 * @param event
	 */
	@FXML public void clickCopy(ActionEvent event){
		
		// Get password

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
	
	
	@FXML public void clickSetting(ActionEvent event) throws IOException{
		changeScene(event,fxml2);
	}
	@FXML public void clickLogout(ActionEvent event) throws IOException{
		if(alretConfirmation("Do you want to log out?"))
			changeScene(event,fxml3);
	}
	@FXML public void clickSearch(ActionEvent event) throws IOException
	{
		changeScene(event, Settings.SearchScene);
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