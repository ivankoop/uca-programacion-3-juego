package juego.clases;

public class Score 
{
	public static int score = 0;
	public static int killCount = 0;
	public static int life = 3;
	
	public static void addScore(int i)
	{
		score += i;
	}
	
	public static void addKill()
	{
		killCount++;
	}
	
	public static void getHurt()
	{
		life--;
	}
}
