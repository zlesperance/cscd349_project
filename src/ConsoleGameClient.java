import java.util.Scanner;

public class ConsoleGameClient implements GameClient {
	private Scanner kb;
	
	public ConsoleGameClient() {
		this.kb = new Scanner(System.in);
	}

	@Override
	public int makeSelection(String... options) {
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

	@Override
	public void openInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openMap(Maze map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openEngagement(Engagement engagement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void report(String string) {
		System.out.println(string);
	}

	@Override
	public void end() {
		this.kb.close();
	}
	
}
