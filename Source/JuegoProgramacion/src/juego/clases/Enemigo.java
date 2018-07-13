package juego.clases;

import juego.extras.Entidad;
import juego.stages.StageJuego;

public class Enemigo extends Alien implements Entidad
{	
	public long eTime = 0;
	public int idleTime = 1 + (int)(Math.random() * ((4 - 1) + 1));
	public int boundaries = 300;
	public int distance = 62;
	
	public Enemigo()
	{
		life = 3;
		speed = 1;
		dead = false;
		
		do
			x = -100 + (int)(Math.random() * ((1650 - (-100)) + 1));
		while(x > 0 && x < 1366);
		do
			y = -100 + (int)(Math.random() * ((880 - (-100)) + 1));
		while(y > 0 && y < 768);
		
		
		switch(0 + (int)(Math.random() * ((2 - 0) + 1)))
		{
			case 0:
				alienImage = "imagenes/yellowAlien/yellow_pj.png";
				shipFull = "imagenes/yellowAlien/yellow_full_hp.png";
				shipMed = "imagenes/yellowAlien/yellow_med_hp.png";
				shipLow = "imagenes/yellowAlien/yellow_low_hp.png";
				distance = 52;
				break;
				
			case 1:
				alienImage = "imagenes/pinkAlien/pink_pj.png";
				shipFull = "imagenes/pinkAlien/pink_full_hp.png";
				shipMed = "imagenes/pinkAlien/pink_med_hp.png";
				shipLow = "imagenes/pinkAlien/pink_low_hp.png";
				break;
			
			case 2:
				alienImage = "imagenes/blueAlien/blue_pj.png";
				shipFull = "imagenes/blueAlien/blue_full_body.png";
				shipMed = "imagenes/blueAlien/blue_med_body.png";
				shipLow = "imagenes/blueAlien/blue_low_body.png";
				break;
		}
		
		alien = new Sprite();
		alien.setImage(alienImage);
		alien.setPosition(x, y);
		
		
		ship = new Sprite();
		ship.setImage(shipFull);
		ship.setPosition(x - 30, y + distance);
		
		this.ship.facing = "RIGHT";
	}
	
	public void update(double time, Jugador s, Enemigo e)
	{	

		this.shoot();
		this.checkBoundaries(e);
		this.move(time, s);
		this.updateEntity(time);
	}

	public void updateEntity(double time)
	{
		alien.update(time);
		ship.update(time);
	}
	
	public void checkBoundaries(Enemigo e)
	{
		if (this.ship.getX() < (0 - boundaries))
		{
			this.ship.setVelocityX(this.ship.getVelocityX() * -2);
			this.alien.setVelocityX(this.alien.getVelocityX() * -2);
		}
		
		if (this.ship.getX() + this.ship.getWidth() > (1366 + boundaries))
		{
			this.ship.setVelocityX(this.ship.getVelocityX() * -2);
			this.alien.setVelocityX(this.alien.getVelocityX() * -2);
		}
		
		if (this.alien.getY() < (0 - boundaries))
		{	
			this.ship.setVelocityY(this.ship.getVelocityY() * -2);
			this.alien.setVelocityY(this.alien.getVelocityY() * -2);
		}
		
		if (this.ship.getY() + this.ship.getHeight() > (768 + boundaries))
		{
			this.ship.setVelocityY(this.ship.getVelocityY() * -2);
			this.alien.setVelocityY(this.alien.getVelocityY() * -2);
		}
	}
	
	public void move(double time, Jugador s)
	{	
		if (this.alien.getX() > s.getX() + s.ship.getWidth())
		{
			this.alien.setVelocityX(-speed);
			this.ship.setVelocityX(-speed);
			this.ship.facing = "LEFT";
		}
		if (this.alien.getY() > s.getY()+ s.ship.getWidth())
		{
			this.alien.setVelocityY(-speed);
			this.ship.setVelocityY(-speed);
		}
		if (this.alien.getX() < s.getX()+ s.ship.getWidth())
		{
			this.alien.setVelocityX(speed);
			this.ship.setVelocityX(speed);
			this.ship.facing = "RIGHT";
		}
		if (this.alien.getY() < s.getY()+ s.ship.getWidth())
		{
			this.alien.setVelocityY(speed);
			this.ship.setVelocityY(speed);
		}
	}
	
	public void shoot() 
	{
		long sTime = (System.currentTimeMillis() / 1000);
		if (eTime < sTime)
		{
			eTime = sTime + this.idleTime;
			this.attacking = false;
		}
		
		if (sTime == eTime && !this.attacking)
		{
			StageJuego.grupoProyectiles.add(new Proyectil(this.ship, true));
			this.attacking = true;
		}


	}
	
	public void getHurt()
	{
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
