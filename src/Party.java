import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Party<T extends Character> implements Iterable<T> {
	private PriorityQueue<T> members;
	private Game game;
	
	public Party() {
		this.members = new PriorityQueue<T>(4, new SpeedComparator());
		this.game = Game.getInstance();
	}
	
	public Party(T[] members) {
		this();
		for (T member : members) {
			addMember(member);
		}
	}
	
	public void addMember(T member) {
		if (members.contains(member))
			throw new IllegalArgumentException("Character already exists in the party");
		members.add(member);
	}
	
	public void removeMember(T member) {
		if (this.size() == 1) {
			throw new IllegalStateException("A party cannot be empty");
		}
		members.remove(member);
	}
	
	public int size() {
		return this.members.size();
	}
	
	public boolean isDefeated() {
		for (T member : this.members) {
			if (!member.isDead())
				return false;
		}
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		return this.members.iterator();
	}
	
	public T selectCharacter() {
		ArrayList<T> characters = new ArrayList<T>(this.members.size());
		String[] options = new String[this.members.size()];
		int i = 0;
		for (T character : this.members) {
			options[i++] = character.toString();
			characters.add(character);
		}
		
		int selection = this.game.makeSelection(options);
		return characters.get(selection);
	}
	
	public T selectCharacter(CharacterTester tester) {
		ArrayList<T> characters = new ArrayList<T>();
		ArrayList<String> options = new ArrayList<String>();
		for (T member : this.members) {
			if (tester.test(member)) {
				characters.add(member);
				options.add(member.toString() + " [" + member.getHP() + " HP]");
			}
		}
		
		int selection = this.game.makeSelection(options.toArray(new String[options.size()]));
		return characters.get(selection);
	}
	
	public T selectRandomCharacter() {
		int index = (int) (this.game.nextRandom() * this.members.size());
		int i = 0;
		for (T character : this.members) {
			if (i == index)
				return character;
			i++;
		}
		throw new IndexOutOfBoundsException("RNG selected an out of bounds index. Whoops!");
	}
	
	public T selectRandomLivingCharacter() {
		if (isDefeated())
			throw new RuntimeException("No characters left");
		int index = (int) (this.game.nextRandom() * this.members.size());
		ArrayList<T> characters = new ArrayList<T>(this.members.size());
		for (T character : this.members) {
			characters.add(character);
		}
		while (characters.get(index).isDead()) {
			index = ((index + 1) % this.members.size());
		}
		return characters.get(index);
	}
}
