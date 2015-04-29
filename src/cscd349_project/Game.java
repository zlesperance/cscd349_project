package cscd349_project;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private static Random rng = new Random();
	
	public static int makeSelection(String... options) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Choose an option:");
		for (int i = 0; i < options.length; i++) {
			System.out.println(" " + i + ") " + options[i]);
		}
		
		int selection;
		do {
			System.out.print(">> ");
			selection = kb.nextInt();
			kb.nextLine();
		} while (selection < 0 || selection >= options.length);
		
		kb.close();
		
		return selection;
	}
	
	public static void report(String message) {
		System.out.println(message);
	}
	
	public static double nextRandom() {
		return rng.nextDouble();
	}
}
