package juego.main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import juego.stages.StageJuego;

public class Juego extends Application
{
	private long nanoTime;
	private GraphicsContext graphics;
	
	private StageJuego stageJuego = new StageJuego();
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception 
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MenuView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		/*graphics = stageJuego.graphics;
		nanoTime = System.nanoTime();

		new AnimationTimer()
		{
			public void handle(long currentNanoTime) 
			{
				System.out.println("bholas");
				double time = (currentNanoTime - nanoTime) / 1000000000D;
				nanoTime = currentNanoTime;
				
				stageJuego.updateStageJuego(time);
				graphics.clearRect(0, 0, stageJuego.getWidth(), stageJuego.getHeight());
				stageJuego.renderStageJuego(graphics);
			}
			
		}.start();
		
		stageJuego.show();*/
	}
}
