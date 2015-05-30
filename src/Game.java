import java.util.Random;
import java.util.Scanner;

public class Game {
	private static Game instance;
	private boolean active = false;
	private Random rng;
	private Inventory inventory;
	private GameClient gameClient;
	private Maze maze;
	
	private Game() {
		rng = new Random();
		inventory = new Inventory();
		active = true;
	}
	
	public static synchronized Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public void registerGameClient(GameClient gameClient) {
		this.gameClient = gameClient;
	}
	
	public int makeSelection(String... options) {
		checkGameActive();
		
		return this.gameClient.makeSelection(options);
	}
	
	public void report(String message) {
		System.out.println(message);
	}
	
	public void reportLocation(int x, int y) {
		report("You are at row " + x + ", column " + y + ".");
	}
	
	public void openInventory() {
		
	}
	
	public double nextRandom() {
		return rng.nextDouble();
	}
	
	private void checkGameActive() {
		if (!this.active || this.gameClient == null)
			throw new GameNotInitializedException();
	}
	
	public void end() {
		checkGameActive();
		this.gameClient.end();
		this.active = false;
		report("Game Over");
	}
}
