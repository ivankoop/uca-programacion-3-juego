package juego.stages;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import juego.clases.Enemigo;
import juego.clases.Jugador;
import juego.clases.Proyectil;
import juego.clases.Score;
import juego.clases.Sprite;

public class StageJuego extends Stage 
{
	public static boolean gameOver;
	private Sprite background;
	public static Jugador alien;
	private ArrayList<String> input;
	public static ArrayList<Proyectil> grupoProyectiles;
	public GraphicsContext graphics;
	public ArrayList<Enemigo> grupoEnemigos;
	public LinkedList<Sprite> lifeIndicator;
	public String hud = "imagenes/greenAlien/hud_p1.png";
	public Sprite hud1;
	public Sprite hud2;
	public Sprite hud3;

	
	public int enemyQuantity = 4;
	
	public StageJuego()
	{	
	
		
		gameOver = false;
		
		
		
		
		
		lifeIndicator = new LinkedList<Sprite>();
		
		hud1 = new Sprite();
		hud1.setImage(hud);
		hud1.setPosition(30, 20);
		
		hud2 = new Sprite();
		hud2.setImage(hud);
		hud2.setPosition(90, 20);
		
		hud3 = new Sprite();
		hud3.setImage(hud);
		hud3.setPosition(150, 20);
		
		lifeIndicator.add(hud1);
		lifeIndicator.add(hud2);
		lifeIndicator.add(hud3);

		
		background = new Sprite();
		background.setImage("imagenes/placeHolder.png");
		background.setPosition(0, 0);
		
		alien = new Jugador();
		input = new ArrayList<String>();
		grupoProyectiles = new ArrayList<Proyectil>();
		
		grupoEnemigos = new ArrayList<Enemigo>();
		
		this.setTitle("Alien Hunt 2000");
		this.centerOnScreen();
		this.setWidth(1366);
		this.setHeight(768);
		this.setResizable(false);
		
		Group base = new Group();
		Scene scene = new Scene(base);
		this.setScene(scene);
		
		Canvas canvas = new Canvas(1366, 768);
		base.getChildren().add(canvas);
		
		this.graphics = canvas.getGraphicsContext2D();
		
		scene.setOnKeyPressed(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						if (!input.contains(code))
						{
							input.add(code);
						}
					}
				}
		);
		
		scene.setOnKeyReleased(
				new EventHandler<KeyEvent>()
				{
					public void handle(KeyEvent e)
					{
						String code = e.getCode().toString();
						input.remove(code);
					}
				}
		);
	}
	
	public void updateStageJuego(double time)
	{
		if (alien.dead) 
		{
			gameOver = true;
		}
		
		switch(Score.score)
		{
			case 100:
			case 200:
			case 300:
			case 400:
				enemyQuantity = (Score.score / 100) + 4;
				break;
		}
		
		switch(Score.life)
		{
			case 3:
				break;
			case 2:
				lifeIndicator.remove(hud3);
				break;
			case 1:
				lifeIndicator.remove(hud2);
				break;
			case 0:
				lifeIndicator.remove(hud1);
				break;
		}

		if (grupoEnemigos.size() < enemyQuantity)
		{
			for (int i = 0; i < (enemyQuantity - grupoEnemigos.size()); i++) 
			{
				grupoEnemigos.add(new Enemigo());
			}
		}
		
		background.update(time);
		
		//alien.updateEntidad(time, input);
		alien.update(time, input);
		for (Proyectil p : grupoProyectiles)
		{
			p.update(time);
			p.update();
			if (p.dead)
			{
				grupoProyectiles.remove(p);
			}
			if (!p.isEvil)
			{
				for (Enemigo e : grupoEnemigos) 
				{
					if (p.intersects(e.ship) || p.intersects(e.alien)) 
					{
						String musicFile = "hurt.wav";
						Media sound = new Media(new File(musicFile).toURI().toString());
						MediaPlayer mediaPlayer = new MediaPlayer(sound);
						mediaPlayer.play();
						e.getHurt();
						grupoProyectiles.remove(p);
					}
				}	
			} 
			else 
			{
				if (p.intersects(alien.ship) || p.intersects(alien.alien))
				{
					String musicFile = "hurt1.wav";
					Media sound = new Media(new File(musicFile).toURI().toString());
					MediaPlayer mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.play();
					alien.getHurt();
					grupoProyectiles.remove(p);
				}
			}
		}
		for (Enemigo e : grupoEnemigos)
		{
			e.update(time, alien, e);
			if (e.dead)
			{
				Score.addKill();
				Score.addScore(10);
				grupoEnemigos.remove(e);
			}
			//e.updateEntidad(time, alien);
			//e.collideEnemies(grupoEnemigos);
		}
		for (Sprite s : lifeIndicator)
		{
			s.update(time);
		}
	}
	
	public void renderStageJuego(GraphicsContext graphics)
	{
		System.out.println(grupoProyectiles.size());
		
		background.render(graphics);
		
		alien.ship.render(graphics);
		alien.alien.render(graphics);

		for (Proyectil p : grupoProyectiles)
		{
			p.render(graphics);
		}
		for (Enemigo e : grupoEnemigos)
		{
			e.ship.render(graphics);
			e.alien.render(graphics);
		}
		printScore(graphics);
		for (Sprite s : lifeIndicator)
		{
			s.render(graphics);
		}
		
	}
	
	public void printScore(GraphicsContext graphics)
	{
		String text = "Score: " + Score.score;
		graphics.setFill( Color.rgb(7, 209, 89) );
		graphics.setLineWidth(2);
		Font theFont = Font.font( "Silon", FontWeight.BOLD, 42 );
		graphics.setFont( theFont );
		graphics.fillText(text, 1115, 50 );
	}
}
