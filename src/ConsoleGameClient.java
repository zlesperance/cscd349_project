import java.util.LinkedList;
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
	public Item openInventory(Inventory inventory) {
		String command = "";
		LinkedList<Item> currentTab = inventory.getItems();
		int currentTabIndex = 0;
		boolean printInv = true;
		
		do {
			if (printInv) {
				printInventoryScreen(currentTab, currentTabIndex);			
				report("Enter item index, tab name, close, or other command (Enter help for commands)");
				printInv = false;
			}
			
			System.out.print(">> ");
			command = kb.nextLine();
			
			if (command.equalsIgnoreCase("close"))
				return null;
			
			if (command.equalsIgnoreCase("help")) {
				report("Commands:");
				report("Enter a number representing an item to use that item (Ex: 1 uses item at index 1)");
				report("Enter then name of a tab to open said tab (Ex: >> EQUIP opens the EQUIP tab)");
				report("Other commands: close, help");
			} else if (command.equalsIgnoreCase("items")) {
				currentTab = inventory.getItems();
				currentTabIndex = 0;
				printInv = true;
			} else if (command.equalsIgnoreCase("equip")) {
				currentTab = inventory.getEquip();
				currentTabIndex = 1;
				printInv = true;			
			} else if (command.equalsIgnoreCase("gears")) {
				currentTab = inventory.getGears();
				currentTabIndex = 2;
				printInv = true;			
			} else if (command.equalsIgnoreCase("etc")) {
				currentTab = inventory.getEtc();
				currentTabIndex = 3;
				printInv = true;
			} else {
				int itemIndex;
				try {
					itemIndex = Integer.parseInt(command);
					
					if (itemIndex < 0 || itemIndex > currentTab.size())
						report("Invalid item number (must be greater than or equal to 0 and less than " + currentTab.size());
					else {
						Item item = currentTab.get(itemIndex);
						return item;
					}
					
				} catch (NumberFormatException e) {
					report("Invalid command (type help for list of commands");
				}
			}
		} while (true);
	}
	
	private void printInventoryScreen(LinkedList<Item> tab, int tabIndex) {
		report("=================================");
		report("======= I N V E N T O R Y =======");
		report("=================================");
		report("| ITEMS | EQUIP | GEARS |  ETC  |");

		String tabFooter = (tabIndex == 0) ? " " : "`";
		
		for(int i = 0; i < 4; i++) {
			if (i == tabIndex)
				tabFooter += "         ";
			else {
				tabFooter += "````````";
				tabFooter += (i + 1 == tabIndex) ? " " : "`";
			}
		}
		report(tabFooter);
		
		int i = 0;
		for(Item item : tab) {
			report(i + ") " + item.toString());
		}
	}

	@Override
	public void openMap(Maze map) {
		
	}

	@Override
	public void openEngagement(Engagement engagement) {
		engagement.begin();
	}

	@Override
	public void report(String string) {
		System.out.println(string);
	}

	@Override
	public void end() {
		this.kb.close();
	}

	@Override
	public Party openCharacterSelect() {
		Party party = new Party();
		int choice;

		this.addNewCharacter(party);
		do {			
			choice = this.makeSelection("Add Another Member", "List Party Members", "Remove Party Member", "End Party Creation");
			
			if (choice == 0) {
				this.addNewCharacter(party);
			} else if (choice == 1) {
				report("Current Party Members: ");
				for (Character member : party) {
					report(" - " + member.toString());
				}
			} else if (choice == 2) {
				Character removal = party.selectCharacter();
				report("Are you sure you want to remove " + removal.toString() + "?");
				int result = this.makeSelection("Yes", "No");
				if (result == 0) {
					try {
						party.removeMember(removal);
					} catch (Exception e) {
						report("Error: " + e.getMessage());
					}
				}
			}
			
		} while (party.size() < Game.MAX_PARTY_SIZE && choice != 3);
		return party;
	}
	
	private void addNewCharacter(Party party) {
		CharacterFactory factory = new HeroesAndMonstersCharacterFactory();
		this.report("Select Your Character's Class:");
		
		String[] classes = new String[] { "Fighter", "Fencer", "Hunter", "Mancer" };
		
		int choice = this.makeSelection(classes);
		
		this.report("Name Your Character:");
		String name = "";
		
		do {
			name = kb.nextLine();
		} while (name.trim().equals(""));
		
		party.addMember(factory.buildProtagonist(classes[choice], name));
	}
	
}
