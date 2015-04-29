package cscd349_project;

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
}
