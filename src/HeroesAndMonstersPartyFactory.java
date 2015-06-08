
public class HeroesAndMonstersPartyFactory implements PartyFactory {
	private CharacterFactory characterFactory;
	
	public HeroesAndMonstersPartyFactory() {
		this.characterFactory = new HeroesAndMonstersCharacterFactory();
	}
	
	public Party makeParty(String type, String... members) {
		if (type.equalsIgnoreCase("protagonists") || type.equalsIgnoreCase("heroes"))
			return makeProtagonistParty(members);
		
		if (type.equalsIgnoreCase("antagonists") || type.equalsIgnoreCase("monsters"))
			return makeAntagonistParty(members);
		
		throw new IllegalArgumentException("Invalid Party Type: " + type);
	}
	
	private Party makeProtagonistParty(String... members) {
		Party party = new Party();
		for (String member : members) {
			String[] info = member.split(",");
			if (info.length == 2)
				party.addMember(this.characterFactory.buildProtagonist(info[0], info[1]));
		}
		return party;
	}
	
	private Party makeAntagonistParty(String... members) {
		Party party = new Party();
		for (String member : members) {
			String[] info = member.split(":");
			if (info.length == 1)
				party.addMember(this.characterFactory.buildAntagonist(info[0]));
			else if (info.length == 2 && info[1].equalsIgnoreCase("key"))
				party.addMember(this.characterFactory.buildAntagonist(info[0], true));
		}
		return party;
	}
}
