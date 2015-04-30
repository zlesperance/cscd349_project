package cscd349_project;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private static Random rng = new Random();
	private static Scanner kb = new Scanner(System.in);
	
	public static int makeSelection(String... options) {
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
	
	public static double nextRandom() {
		return rng.nextDouble();
	}
	
	public static void end() {
		kb.close();
	}
}
