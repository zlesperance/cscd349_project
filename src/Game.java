import java.util.Random;
import java.util.Scanner;

public class Game {
	private static boolean active = false;
	private static Random rng;
	private static Scanner kb;
	private static Inventory inventory;
	
	public static int makeSelection(String... options) {
		checkGameActive();
		System.out.println("Choose an option:");
		for (int i = 0; i < options.length; i++) {
			System.out.println(" " + i + ") " + options[i]);
		}
		
		int selection;
		do {
			System.out.print(">> ");
			try {
				selection = Integer.parseInt(kb.nextLine());
			} catch (NumberFormatException e) {
				selection = -1;
			}
		} while (selection < 0 || selection >= options.length);
		
		return selection;
	}
	
	public static void report(String message) {
		System.out.println(message);
	}
	
	public static void reportLocation(int x, int y) {
		report("You are at row " + x + ", column " + y + ".");
	}
	
	public static void openInventory() {
		
	}
	
	public static double nextRandom() {
		return rng.nextDouble();
	}
	
	private static void checkGameActive() {
		if (!active)
			throw new GameNotInitializedException();
	}
	
	public static void start() {
		rng = new Random();
		kb = new Scanner(System.in);
		inventory = new Inventory();
		active = true;
	}
	
	public static void end() {
		checkGameActive();
		kb.close();
		active = false;
		report("Game Over");
	}
}
