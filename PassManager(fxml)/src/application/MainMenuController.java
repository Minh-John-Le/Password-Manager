package application;
	

import java.io.IOException;
import java.util.ArrayList;

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
	private final User user = LoginController.user;
	@FXML
	private Label userLabel;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> appPass;
	ArrayList accountList = DAO.SearchAccountDAO.getAllAccount(user.getUserID());

	//this function set the user name that successfully logs in 
	//and sends it to main menu. so the main menu would be able to 
	//show account info associated with this user name
	public ObservableList<Account> list;
	
	@FXML
	public void initialize() {
		list = FXCollections.observableArrayList(accountList);
		
		String labelString = user.getUserName();
		userLabel.setText(labelString);
	    //Initializing the table
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(list);
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
		if(alretConfirmation(messege))
			changeScene(event,fxml3);
	}
	@FXML public void clickSearch(ActionEvent event) throws IOException{
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