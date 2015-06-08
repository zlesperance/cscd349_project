public class HeroesAndMonstersCharacterFactory implements CharacterFactory {
	public Protagonist buildProtagonist(String characterClass, String characterName) {
		if (characterClass.equalsIgnoreCase("fighter"))
			return new Fighter(characterName);
		if (characterClass.equalsIgnoreCase("fencer"))
			return new Fencer(characterName);
		if (characterClass.equalsIgnoreCase("hunter"))
			return new Hunter(characterName);
		if (characterClass.equalsIgnoreCase("mancer"))
			return new Mancer(characterName);
		
		throw new IllegalArgumentException("Invalid Character Class");
	}
	
	@Override
	public Antagonist buildAntagonist(String characterClass) {
		if (characterClass.equalsIgnoreCase("slime"))
			return new Slime();
		if (characterClass.equalsIgnoreCase("newt"))
			return new Newt();
		if (characterClass.equalsIgnoreCase("ghost"))
			return new Ghost();
		if (characterClass.equalsIgnoreCase("anarchist"))
			return new Anarchist();
		
		throw new IllegalArgumentException("Invalid Character Class");
	}

	@Override
	public Antagonist buildAntagonist(String characterClass, boolean key) {		
		Antagonist monster = buildAntagonist(characterClass);
		
		if (key == true)
			monster.makeKeyBearer();
		
		return monster;
	}
}
