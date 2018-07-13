package application;

public class Player {
	private String name;
	private int score;
	public Player(String n, int s){
		this.name = n;
		this.score = s;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
