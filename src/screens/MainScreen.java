package screens;
import controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainScreen extends Application{
	
	@Override
	public void start(Stage stage) {

	try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/screens/MainScreen.fxml"));
		loader.setController(new MainScreenController());
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setTitle("Page Replacement Demonstration");
		stage.setResizable(false);
		 
		stage.setOnCloseRequest(event -> {
			event.consume();
			logoutPressed(stage);	
		});stage.show();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
}	

public void logoutPressed(Stage stage){	
	
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Logout");
	alert.setHeaderText("You're about to exit!");
	alert.setContentText("See you next time !!!");
	
	if (alert.showAndWait().get() == ButtonType.OK){
		stage.close();
	} 
}
	public static void main(String[] args) {
		launch(args);
	}
}