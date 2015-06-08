import java.util.Random;

public class Game {
	private static Game instance;
	private boolean active = false;
	private Random rng;
	private ItemDatabase itemDatabase;
	private Inventory inventory;
	private GameClient gameClient;
	private Party protagonists;
	private StartMaze maze;
	public static final int MAX_PARTY_SIZE = 4;
	
	
	private Game() {
		rng = new Random();
		inventory = new Inventory();
		itemDatabase = new ItemDatabase();
		this.maze = new StartMaze();
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
	
	public void start() {
		this.selectProtagonists();
		this.maze.startGame();
	}
	
	public void selectProtagonists() {
		this.protagonists = this.gameClient.openCharacterSelect();
	}
	
	public Party getProtagonists() {
		return this.protagonists;
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
	
	public Item openInventory() {
		return this.gameClient.openInventory(inventory);
	}
	
	public Item getItemByID(int id) {
		return this.itemDatabase.getItem(id);
	}
	
	public void addToInventory(Item... items) {
		for (Item item : items) {
			this.inventory.addItem(item);
		}
	}
	
	public void useItem(Item item) {
		try {
			item.use(this.inventory, this.protagonists, this.maze);
		} catch (Exception e) {
			report(e.getMessage());
		}
	}
	
	public void beginEngagement(Party antagonists) {
		Engagement engagement = new Engagement(this.protagonists, antagonists);
		this.gameClient.openEngagement(engagement);
		Item[] spoils = engagement.lootSpoils();
		for (Item spoil : spoils) {
			report("You received a " + spoil.toString());
			this.inventory.addItem(spoil);
		}
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
	
	public static void main(String[] args) {
		Game game = Game.getInstance();
		game.registerGameClient(new ConsoleGameClient());
		game.start();
		game.end();
	}
}
