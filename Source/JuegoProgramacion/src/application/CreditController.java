package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreditController {
	public void initialize(){
		System.out.println("Se iniciaron los creditos");
	}
	
	@FXML
	private void OnBack(ActionEvent event) {
	  System.out.println("atras");
	  try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
	                Parent root1 = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                Scene scene = new Scene(root1);
	                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
	                stage.setScene(scene);  
	                stage.show();
					((Node)(event.getSource())).getScene().getWindow().hide();
					
	        } catch(Exception e) {
	           e.printStackTrace();
	        }
	}

}
