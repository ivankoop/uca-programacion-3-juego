package application;



import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import juego.stages.StageJuego;



public class MenuController {
	private long nanoTime;
	private GraphicsContext graphics;
	
	private StageJuego stageJuego;

	@FXML
	private void OnPlay(ActionEvent event) {
		stageJuego = new StageJuego();
	  graphics = stageJuego.graphics;
	  nanoTime = System.nanoTime();

	
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
	
	@FXML
	private void onHighScore(ActionEvent event){
		System.out.println("high Score");
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HighScoreView2.fxml"));
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
	
	@FXML
	private void onCredit(ActionEvent event){
		System.out.println("Creditos");
		try {
	        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreditView.fxml"));
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
