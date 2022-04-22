package application;

import javafx.fxml.FXML;
import java.io.IOException;

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
	

	//this function set the user name that successfully logs in 
	//and sends it to main menu. so the main menu would be able to 
	//show account info associated with this user name
	public ObservableList<Account> list = FXCollections.observableArrayList(
			new Account(1,2,"google","Johren87","1234","john_smith@gmail.com","01/01/2020","06/01/2020","180")
	);
	@FXML
	public void initialize() {
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
		//update the table
		changeScene(event,"SearchMenu.fxml");

	}

}
