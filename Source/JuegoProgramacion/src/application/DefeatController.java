package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import juego.clases.Score;
import juego.stages.StageJuego;

public class DefeatController implements Initializable {
	// referencias a componentes del archivo DefeatView.fxml
	private long nanoTime;
	private GraphicsContext graphics;
	
	private StageJuego stageJuego = new StageJuego();
	@FXML private javafx.scene.control.Button again_btn;
	@FXML private javafx.scene.control.Button menu_btn;
	@FXML private javafx.scene.control.Button clear_btn;
	@FXML private javafx.scene.control.Label Score_data;
	@FXML private javafx.scene.control.TextField cuar_txfield;
	@FXML private javafx.scene.control.TextField ter_txfield;
	@FXML private javafx.scene.control.TextField seg_txfield;
	@FXML private javafx.scene.control.TextField prim_txfield;
	public ArrayList<String> txfields;
	private String full_name;
	
	//Este metodo se ejecuta cuando se inicializa la vista de Perdiste.
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("Se inicio la vista defeat");
		Score_data.setText(Integer.toString(Score.score));
		again_btn.setDisable(true);
		menu_btn.setDisable(true);
		txfields = new ArrayList<>();
		
	}
	//Listener del boton limpiar de la vista Perdiste.
	@FXML
	private void OnClear(ActionEvent event){
		prim_txfield.setDisable(false);
		seg_txfield.setDisable(false);
		ter_txfield.setDisable(false);
		cuar_txfield.setDisable(false);
		prim_txfield.setText("");
		seg_txfield.setText("");
		ter_txfield.setText("");
		cuar_txfield.setText("");
		again_btn.setDisable(true);
		menu_btn.setDisable(true);
		txfields.removeAll(txfields);
	}
	//Listener del boton jugar de nuevo de la vista Perdiste.
	@FXML
	private void OnAgain(ActionEvent event){
		System.out.println(full_name);
		System.out.println(Score_data.getText()); 
		  graphics = stageJuego.graphics;
		  nanoTime = System.nanoTime();
			try {
				 new HighScore(full_name,Score.score);
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	stageJuego.gameOver = false;
		  	Score.score = 0;
		  	Score.killCount = 0;
		  	Score.life = 3;
			new AnimationTimer()
			{
				public void handle(long currentNanoTime) 
				{
					double time = (currentNanoTime - nanoTime) / 1000000000D;
					nanoTime = currentNanoTime;
					
					stageJuego.updateStageJuego(time);
					graphics.clearRect(0, 0, stageJuego.getWidth(), stageJuego.getHeight());
					stageJuego.renderStageJuego(graphics);
					
					if (StageJuego.gameOver)
					{
						this.stop();
						stageJuego.close();
						showDefeat();
					}
				}
				
			}.start();
			
			stageJuego.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public void showDefeat()
	{
		 stageJuego.close();
		  try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DefeatView.fxml"));
             Parent root1 = (Parent) fxmlLoader.load();
             Stage stage = new Stage();
             Scene scene = new Scene(root1);     
             scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
             stage.setScene(scene);  
             stage.show();
		        } catch(Exception e) {
		           e.printStackTrace();
		        }
	}
	//Listener del primer txfield de la vista Perdiste.
	@FXML
	private void Onkeyprim(){
		if(prim_txfield.getText().trim().isEmpty()){
			System.out.println("Esta vacio");
		} else {
			prim_txfield.setDisable(true);
			txfields.add(prim_txfield.getText());
			if(txfields.size() == 4){
				again_btn.setDisable(false);
				menu_btn.setDisable(false);
				full_name = txfields.get(0) + txfields.get(1) + txfields.get(2) + txfields.get(3);
			}
		}
	}
	//Listener del segundo txfield de la vista Perdiste.
	@FXML
	private void Onkeyseg(){
		if(seg_txfield.getText().trim().isEmpty()){
			System.out.println("Esta vacio");
		} else {
			seg_txfield.setDisable(true);
			txfields.add(seg_txfield.getText());
			if(txfields.size() == 4){
				again_btn.setDisable(false);
				menu_btn.setDisable(false);
				full_name = txfields.get(0) + txfields.get(1) + txfields.get(2) + txfields.get(3);
			}
		}
	}
	//Listener del tercer txfield de la vista Perdiste.
	@FXML
	private void Onkeyter(){
		if(ter_txfield.getText().trim().isEmpty()){
			System.out.println("Esta vacio");
		} else {
			ter_txfield.setDisable(true);
			txfields.add(ter_txfield.getText());
			if(txfields.size() == 4){
				again_btn.setDisable(false);
				menu_btn.setDisable(false);
				full_name = txfields.get(0) + txfields.get(1) + txfields.get(2) + txfields.get(3);
			}
		}
	}
	//Listener del cuarto txfield de la vista Perdiste.
	@FXML
	private void Onkeycuar(){
		if(cuar_txfield.getText().trim().isEmpty()){
			System.out.println("Esta vacio");
		} else {
			cuar_txfield.setDisable(true);
			txfields.add(cuar_txfield.getText());
			if(txfields.size() == 4){
				again_btn.setDisable(false);
				menu_btn.setDisable(false);
				full_name = txfields.get(0) + txfields.get(1) + txfields.get(2) + txfields.get(3);
			}
		}
	}
	
	//Listener del boton Menu de la vista Perdiste.
	@FXML
	private void OnMenu(ActionEvent event){
		try {
			 new HighScore(full_name,Score.score);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		stageJuego.gameOver = false;
	  	Score.score = 0;
	  	Score.killCount = 0;
	  	Score.life = 3; 
	  	System.out.println(Score.score); 
		 try {
			 	//cargamos el archivo XML
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
		                Parent root1 = (Parent) fxmlLoader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root1);
		                scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
		                stage.setScene(scene);  
		                stage.setResizable(false);
		                stage.setTitle("Alien Hunt 2000");
		                stage.show();
		                //escondemos la vista anterior.
						((Node)(event.getSource())).getScene().getWindow().hide();
						
		        } catch(Exception e) {
		           e.printStackTrace();
		        }
	}


}
