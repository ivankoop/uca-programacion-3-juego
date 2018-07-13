package application;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScore {
	
	public Player new_player; 
	public static String filename = "test.txt";
	public ArrayList<Player> temp_list;
	public ArrayList<Player> list;
	public boolean equal_player = false;
	public int data_lenght = Files.readAllLines(Paths.get(filename)).size();
	public HighScore(String name, int score) throws NumberFormatException, IOException{
		list = new ArrayList<>();
		temp_list = new ArrayList<>();
		this.new_player = new Player(name,score);
		System.out.println(data_lenght);
		fileReader();
		calcFile();
		flushFile();
	} 
	
	
	public void fileReader(){
		if(data_lenght > 0){
			try {
				for (String line : Files.readAllLines(Paths.get(filename))) {
					System.out.println(line);
					String[] parts = line.split(",");
					this.temp_list.add(new Player(parts[0],Integer.parseInt(parts[1])));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block1366x768
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void calcFile(){
		if(data_lenght > 0){
			for(Player a : temp_list){
				if(a.getName().equalsIgnoreCase(this.new_player.getName())){
					System.out.println("hay igual");
					equal_player = true;
					if(a.getScore() < this.new_player.getScore()){
						a.setScore(this.new_player.getScore());
					}
				}
			}
			if(!equal_player){
				list.add(this.new_player);
				for(Player a : temp_list){
					list.add(a);
				}
			} else {
				for(Player a : temp_list){
					list.add(a);
				}
			}
		} else {
			list.add(this.new_player);
		}
	}
	
	public void flushFile(){
		Collections.sort(list, new PlayerComparator());
		try {
			PrintWriter printer = new PrintWriter(filename);
			for(Player s : list){
				printer.println(s.getName() + "," + s.getScore());
			}
			printer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Player> getTopTenPlayers(){
		int c = 0;
		ArrayList<Player> top_ten_players = new ArrayList<>();
		try {
			for (String line : Files.readAllLines(Paths.get(filename))) {
				c++;
				if(c<11){
					System.out.println(line);
					String[] parts = line.split(",");
					top_ten_players.add(new Player(parts[0],Integer.parseInt(parts[1])));
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return top_ten_players;
	}
}
