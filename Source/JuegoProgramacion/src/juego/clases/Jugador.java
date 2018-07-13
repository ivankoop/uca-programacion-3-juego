package juego.clases;

import java.util.ArrayList;

import juego.extras.Entidad;
import juego.stages.StageJuego;

public class Jugador extends Alien implements Entidad
{	
	
	public Jugador()
	{	
		speed = 20;
		attacking = true;
		
		x = 1366/2;
		y = 768/2;
		
		life = 3;
		dead = false;
		
		alienImage = "imagenes/greenAlien/greenAlien.png";
		shipFull = "imagenes/greenAlien/greenAlienFullShip.png";
		shipMed = "imagenes/greenAlien/greenAlienMedShip.png";
		shipLow = "imagenes/greenAlien/greenAlienLowShip.png";
		
		alien = new Sprite();
		alien.setImage(alienImage);
		alien.setPosition(x, y);
		
		ship = new Sprite();
		ship.setImage(shipFull);
		ship.setPosition(x - 30, y + 62);
		
		this.ship.facing = "RIGHT";
	}
	
	public void update(double time, ArrayList<String> input)
	{
		this.checkBoundaries();
		this.move(input, time);
		this.updateEntity(time);
	}
	
	public void updateEntity(double time)
	{
		alien.update(time);
		ship.update(time);
	}
	
	public void checkBoundaries()
	{
		if (this.ship.getX() < 0)
		{
			this.ship.setVelocityX(this.ship.getVelocityX() * -2);
			this.alien.setVelocityX(this.alien.getVelocityX() * -2);
		}
		
		if (this.ship.getX() + this.ship.getWidth() > 1366)
		{
			this.ship.setVelocityX(this.ship.getVelocityX() * -2);
			this.alien.setVelocityX(this.alien.getVelocityX() * -2);
		}
		
		if (this.alien.getY() < 0)
		{	
			this.ship.setVelocityY(this.ship.getVelocityY() * -2);
			this.alien.setVelocityY(this.alien.getVelocityY() * -2);
		}
		
		if (this.ship.getY() + this.ship.getHeight() > 768)
		{
			this.ship.setVelocityY(this.ship.getVelocityY() * -2);
			this.alien.setVelocityY(this.alien.getVelocityY() * -2);
		}
			
	}
	
	public void move(ArrayList<String> input, double time)
	{
		/*this.alien.setVelocity(0, 0);
		this.ship.setVelocity(0, 0);*/

		this.alien.decreaseVelocity(time);
		this.ship.decreaseVelocity(time);
		

		
		if (input.contains("LEFT"))
		{
			this.alien.setVelocityX(-this.speed);
			this.ship.setVelocityX(-this.speed);  
			this.ship.facing = "LEFT";
		}
		else if (input.contains("RIGHT"))
		{
			this.alien.setVelocityX(this.speed);
			this.ship.setVelocityX(this.speed);
			this.ship.facing = "RIGHT";
		}
		else if (input.contains("UP"))
		{
			this.alien.setVelocityY(-this.speed);
			this.ship.setVelocityY(-this.speed);
		}
		else if (input.contains("DOWN"))
		{
			this.alien.setVelocityY(this.speed);
			this.ship.setVelocityY(this.speed);
		}
		if (input.contains("SPACE"))
			this.shoot();
		else
			this.attacking = false;
		
		if (input.contains("R"))
		{
			this.alien.setPosition(1366/2, 768/2);
			this.ship.setPosition((1366/2) - 30, (768/2) + 62);
		}

	}

	public void shoot() 
	{
		if (!this.attacking)
		{
			StageJuego.grupoProyectiles.add(new Proyectil(this.ship, false));
			this.attacking = true;
		}

	}
	
	public double getX()
	{
		return this.alien.getX();
	}
	
	public double getY()
	{
		return this.alien.getY();
	}
	
	public void getHurt()
	{
		Score.life--;
		
		switch(life)
		{
			case 3:
				this.ship.setImage(shipMed);
				life = 2;
				break;
			case 2:
				this.ship.setImage(shipLow);
				life = 1;
				break;
			case 1:
				dead = true;
		}
	}
}
