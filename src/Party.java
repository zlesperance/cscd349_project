import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Party implements Iterable<Character> {
	private PriorityQueue<Character> members;
	private Game game;
	
	public Party() {
		this.members = new PriorityQueue<Character>(4, new SpeedComparator());
		this.game = Game.getInstance();
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
		if (this.size() == 1) {
			throw new IllegalStateException("A party cannot be empty");
		}
		members.remove(member);
	}
	
	public int size() {
		return this.members.size();
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
		ArrayList<Character> characters = new ArrayList<Character>(this.members.size());
		String[] options = new String[this.members.size()];
		int i = 0;
		for (Character character : this.members) {
			options[i++] = character.toString();
			characters.add(character);
		}
		
		int selection = this.game.makeSelection(options);
		return characters.get(selection);
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
		
		int selection = this.game.makeSelection(options.toArray(new String[options.size()]));
		return characters.get(selection);
	}
	
	public Character selectRandomCharacter() {
		int index = (int) (this.game.nextRandom() * this.members.size());
		int i = 0;
		for (Character character : this.members) {
			if (i == index)
				return character;
			i++;
		}
		throw new IndexOutOfBoundsException("RNG selected an out of bounds index. Whoops!");
	}
	
	public Character selectRandomLivingCharacter() {
		if (isDefeated())
			throw new RuntimeException("No characters left");
		int index = (int) (this.game.nextRandom() * this.members.size());
		ArrayList<Character> characters = new ArrayList<Character>(this.members.size());
		for (Character character : this.members) {
			characters.add(character);
		}
		while (characters.get(index).isDead()) {
			index = ((index + 1) % this.members.size());
		}
		return characters.get(index);
	}
}
