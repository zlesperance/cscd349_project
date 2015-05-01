import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Party implements Iterable<Character> {
	private PriorityQueue<Character> members;
	
	public Party() {
		this.members = new PriorityQueue<Character>(4, new SpeedComparator());
	}
	
	public Party(Character[] members) {
		this();
		for (Character member : members) {
			addMember(member);
		}
	}
	
	public void addMember(Character member) {
		if (members.contains(member))
			throw new IllegalArgumentException("Character already exists in the party");
		members.add(member);
	}
	
	public void removeMember(Character member) {
		members.remove(member);
	}
	
	public boolean isDefeated() {
		for (Character member : this.members) {
			if (!member.isDead())
				return false;
		}
		return true;
	}

	@Override
	public Iterator<Character> iterator() {
		return this.members.iterator();
	}
	
	public Character selectCharacter() {
		Character[] characters = this.members.toArray(new Character[this.members.size()]);
		String[] options = new String[characters.length];
		for (int i = 0; i < characters.length; i++) {
			options[i] = characters[i].toString();
		}
		
		int selection = Game.makeSelection(options);
		return characters[selection];		
	}
	
	public Character selectCharacter(CharacterTester tester) {
		ArrayList<Character> characters = new ArrayList<Character>();
		ArrayList<String> options = new ArrayList<String>();
		for (Character member : this.members) {
			if (tester.test(member)) {
				characters.add(member);
				options.add(member.toString() + " [" + member.getHP() + " HP]");
			}
		}
		
		int selection = Game.makeSelection(options.toArray(new String[options.size()]));
		return characters.get(selection);
	}
	
	public Character selectRandomCharacter() {
		int index = (int) (Game.nextRandom() * this.members.size());
		Character[] characters = this.members.toArray(new Character[this.members.size()]);
		return characters[index];
	}
	
	public Character selectRandomLivingCharacter() {
		if (isDefeated())
			throw new RuntimeException("No characters left");
		int index = (int) (Game.nextRandom() * this.members.size());
		Character[] characters = this.members.toArray(new Character[this.members.size()]);
		while (characters[index].isDead()) {
			index = ((index + 1) % this.members.size());
		}
		return characters[index];
	}
}
