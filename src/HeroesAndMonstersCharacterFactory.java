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
		return buildAntagonist(characterClass, null);
	}

	@Override
	public Antagonist buildAntagonist(String characterClass,
			String characterName) {
		if (characterName == null)
			characterName = characterClass;
		
		if (characterClass.equalsIgnoreCase("slime"))
			return new Slime(characterName);
		
		throw new IllegalArgumentException("Invalid Character Class");
	}
}
