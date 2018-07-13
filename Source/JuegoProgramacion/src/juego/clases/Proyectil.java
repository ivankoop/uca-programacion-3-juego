package juego.clases;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Proyectil extends Sprite
{
	private String direction;
	private int speed = 600;
	private int scale = 3;
	public boolean isEvil;
	public boolean dead = false;
	

	
	
	public Proyectil(Sprite s, boolean e)
	{
		this.direction = s.facing;
		Image i = new Image("imagenes/alien/alienBullet.png", 4*this.scale, 4*this.scale, false, true);
		super.setImage(i);
		
		this.isEvil = e;
		
		if (e)
		{
			this.speed = 400;
			String musicFile = "laser2.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}
		else
		{
			String musicFile = "laser5.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
		}
		
		if (this.direction == "LEFT")
		{
			super.setVelocityX(-speed);
			super.setPosition(s.getX(), s.getY());
		}
		else if (this.direction == "RIGHT")
		{
			super.setVelocityX(speed);
			super.setPosition(s.getX() + s.getWidth(), s.getY());
		}
	}
	
	public void update()
	{
		if (this.getX() < -200 || this.getX() > 2000 || this.getY() < -200 || this.getY() > 1000)
			this.dead = true;
	}
}
