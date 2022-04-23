package application;

import javafx.fxml.FXML;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchController extends AppUI{
	private User user = LoginController.user;
	private final String text = "The password is copied.";
	@FXML
	private Label userLabel;
	@FXML
	private TextField application;
	@FXML
	private TextField username;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> appPass;
	ArrayList<Account> allAccountList = DAO.SearchAccountDAO.getAllAccount(user.getUserID());
	

	//this function set the user name that successfully logs in 
	//and sends it to main menu. so the main menu would be able to 
	//show account info associated with this user name
	public ObservableList<Account> list;
	public ObservableList<Account> searchList;
	@FXML
	public void initialize() {
		list = FXCollections.observableArrayList(allAccountList);
		
		String labelString = user.getUserName();
		userLabel.setText(labelString);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(list);
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
		String applicationString = application.getText();
		String usernameString = username.getText();
		ArrayList<Account> searchAccountList =DAO.SearchAccountDAO.getAccount(user.getUserID(),applicationString,usernameString);
		//update the table
		searchList = FXCollections.observableArrayList(searchAccountList);
		
		String labelString = user.getUserName();
		userLabel.setText(labelString);
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(searchList);
		table.getSelectionModel().selectFirst();
	}

}
