package application;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HighScoreController{
	//name labels
	public Label Score1;
	public Label Score2;
	public Label Score3;
	public Label Score4;
	public Label Score5;
	public Label Score6;
	public Label Score7;
	public Label Score8;
	public Label Score9;
	public Label Score10;
	
	//scores labels
	public Label Score1_1;
	public Label Score2_2;
	public Label Score3_3;
	public Label Score4_4;
	public Label Score5_5;
	public Label Score6_6;
	public Label Score7_7;
	public Label Score8_8;
	public Label Score9_9;
	public Label Score10_10;
	public void initialize(){
		System.out.println("se inicio");
		HighScore ivan = null;
		try {
			ivan = new HighScore("test",1);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Player> Play_data = ivan.getTopTenPlayers();
		this.Score1.setText(Play_data.get(0).getName());
		this.Score2.setText(Play_data.get(1).getName());
		this.Score3.setText(Play_data.get(2).getName());
		this.Score4.setText(Play_data.get(3).getName());
		this.Score5.setText(Play_data.get(4).getName());
		this.Score6.setText(Play_data.get(5).getName());
		this.Score7.setText(Play_data.get(6).getName());
		this.Score8.setText(Play_data.get(7).getName());
		this.Score9.setText(Play_data.get(8).getName());
		this.Score10.setText(Play_data.get(9).getName());
		
		this.Score1_1.setText("---------------- " + Play_data.get(0).getScore());
		this.Score2_2.setText("---------------- " + Play_data.get(1).getScore());
		this.Score3_3.setText("---------------- " + Play_data.get(2).getScore());
		this.Score4_4.setText("---------------- " + Play_data.get(3).getScore());
		this.Score5_5.setText("---------------- " + Play_data.get(4).getScore());
		this.Score6_6.setText("---------------- " + Play_data.get(5).getScore());
		this.Score7_7.setText("---------------- " + Play_data.get(6).getScore());
		this.Score8_8.setText("---------------- " + Play_data.get(7).getScore());
		this.Score9_9.setText("---------------- " + Play_data.get(8).getScore());
		this.Score10_10.setText("---------------- " + Play_data.get(9).getScore());
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
