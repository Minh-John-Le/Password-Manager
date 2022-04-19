package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpiredPassController extends AppUI{
	private final String text = "Do You Want To Logout?";
	@FXML
	private Label user;
	@FXML private TableView<Account> table;
	@FXML private TableColumn<Account ,String> appName;
	@FXML private TableColumn<Account ,String> userName;
	@FXML private TableColumn<Account , String> appPass;
	

	//this function set the user name that successfully logs in 
	//and sends it to main menu. so the main menu would be able to 
	//show account info associated with this user name
	public ObservableList<Account> list = FXCollections.observableArrayList(
			new Account("google","Johren87","1234","john_smith@gmail.com","01/01/2020","06/01/2020","180")
	);
	@FXML
	public void initialize() {
		appName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appName"));
		userName.setCellValueFactory(new PropertyValueFactory<Account ,String> ("userName"));
		appPass.setCellValueFactory(new PropertyValueFactory<Account ,String> ("appPass"));
		table.setItems(list);
		table.getSelectionModel().selectFirst();
	}
	@FXML public void click_Edit(ActionEvent event){
		try {
			
			changeScene(event,"AppInfoMenu.fxml");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML public void click_Logout(ActionEvent event){
		try {
			if(alretConfirmation(text))
			closeStage(event,text);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML public void click_Main(ActionEvent event){
		try {
			
			changeScene(event,"MainMenu.fxml");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@FXML public void click_Setting(ActionEvent event){
		try {
			
			changeScene(event,"SettingMenu.fxml");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
