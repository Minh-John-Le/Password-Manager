package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppUI {
	//setup fxml loader
	public FXMLLoader setFXMLLoader(String fxml) {
		FXMLLoader loader = new FXMLLoader((getClass().getResource(fxml)));
		return loader;
	}
	
	//setup root node for scene
	public Parent setRoot(FXMLLoader loader) throws IOException{
		Parent root = loader.load();
		return  root;
	}
	
	//setup customized stage that is centered-screen
	public Stage setStage(Scene scene,String title) throws IOException{
		Stage stage = new Stage();
		//Image icone = new Image("file:obdc.png");
		//stage.getIcons().add(icone);
		stage.setTitle(title);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		((Stage)(scene.getWindow())).centerOnScreen();
		stage.showAndWait();
		return stage;
	}
	//setup new secene
	public Scene setScene(Parent root) {
		Scene scene = new Scene(root);
		return scene;
	}
	
	//close the stage
	public void closeStage(ActionEvent event,String messge) {
		Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
		 stage.close();
	}
	
	//overloading close stage
	public void closeStage(ActionEvent event) {
		Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
		 stage.close();
	}
	
    //change the scene with new one
	//this part of the program is just for prototype proposes. It should be replaced later with better strategy.
	//creating new scene every time the program wants to change the scene creates over head and drains the memory
	// we should come up with strategy to store each scene in its controller object and retrieve it when we need it

	public FXMLLoader changeScene(ActionEvent event,String fxml) throws IOException {
		FXMLLoader loader = setFXMLLoader(fxml);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		return loader;
	}
	public void changeScene(ActionEvent event,FXMLLoader loader) throws IOException {
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	public void changeScene(ActionEvent event,Scene scene) throws IOException {
		Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	//check if field is cleared 
	public Boolean isTextFieldCleared(String text) {
		
		if(text.isEmpty())
			return true;
		return false;
	}
	//check if field is cleared //(don't need it, Van)
	public Boolean isPasswordFieldCleared(String text) {
		
		if(text.isEmpty())
			return true;
		return false;
	}	
	
	public void alretMessege(String str) {
		Alert alert = new Alert(AlertType.INFORMATION);
		//alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}
	//create yes and know confirmation menu
	public Boolean alretConfirmation(String str) throws IOException{
		Alert alert = new Alert(Alert.AlertType.NONE, str, ButtonType.YES, ButtonType.NO);
		alert.setHeaderText(null);
		// clicking X also means no
	    ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
	    if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
	    	return false;
	    }
	return true;
	}
	public void setStage(String fxml,String title) throws IOException {
		setStage(setScene(setRoot(setFXMLLoader(fxml))),title);
	}
	public Scene setScene(String fxml) throws IOException {
		Scene scene = setScene(setRoot(setFXMLLoader(fxml)));
		return scene;
	}
}
