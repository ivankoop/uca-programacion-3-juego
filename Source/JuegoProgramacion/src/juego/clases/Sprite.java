package juego.clases;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite 
{	
	public int life;
	public int speed;
	public String facing;
	
	private Image image;
	private String filename;
	private double positionX;
	private double positionY;    
	private double velocityX;
	private double velocityY;
	private double width;
	private double height;

	public Sprite()
	{
		positionX = 0;
		positionY = 0;    
		velocityX = 0;
		velocityY = 0;
	}
	    
	public String getFilename()
	{
		return this.filename;
	}
	public void setFilename(String filename)
	{
		this.filename = filename;
	}
	
	public double getVelocityX()
	{
		return this.velocityX;
	}
	    
	public double getVelocityY()
	{
		return this.velocityY;
	}
	    
	public void setVelocityX(double x)
	{
		this.velocityX += x;
	}
	    
	public void setVelocityY(double y)
	{
		this.velocityY += y;
	}
	    
	public double getX()
	{
		return this.positionX;
	}
	    
	public double getY()
	{
		return this.positionY;
	}
	    
	public void setX(int x)
	{
		this.positionX = x;
	}
	    
	public void setY(int y)
	{
		this.positionY = y;
	}
	    
	public double getWidth()
	{
		return this.width;
	}
	    
	public double getHeight()
	{
		return this.height;
	}

	public void setImage(Image i)
	{
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	public void setImage(String filename)
	{
		this.filename = filename;
		Image i = new Image(filename);
		setImage(i);	
	}

	public void setPosition(double x, double y)
	{
		positionX = x;
		positionY = y;
	}

	public void setVelocity(double x, double y)
	{
		velocityX = x;
		velocityY = y;
	}

	public void addVelocity(double x, double y)
	{
		velocityX += x;
		velocityY += y;
	}

	public void update(double time)
	{
		positionX += velocityX * time;
		positionY += velocityY * time;
	}
	
	public void decreaseVelocity(double time)
	{
		if (getVelocityX() > 0)
		{
			this.setVelocity(getVelocityX() - (1*5), getVelocityY());
		}
		if (getVelocityY() > 0) 
		{
			this.setVelocity(getVelocityX(), getVelocityY()  - (1*5));
		}
		if (getVelocityX() < 0)
		{
			this.setVelocity(getVelocityX()  + (1*5), getVelocityY());
		}
		if (getVelocityY() < 0) 
		{
			this.setVelocity(getVelocityX(), getVelocityY()  + (1*5));
		}
	}

	public void render(GraphicsContext gc)
	{
		gc.drawImage( image, positionX, positionY );
	}

	public Rectangle2D getBoundary()
	{
		return new Rectangle2D(positionX,positionY,width,height);
	}

	public boolean intersects(Sprite s)
	{
		return s.getBoundary().intersects( this.getBoundary() );
	}
	    
	public String toString()
	{
		return " Position: [" + positionX + "," + positionY + "]" 
				+ " Velocity: [" + velocityX + "," + velocityY + "]";
	}

	public String getFacing() 
	{
		return this.facing;
	}
}
