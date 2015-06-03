
public interface CharacterFactory {
	public Protagonist buildProtagonist(String characterClass, String characterName);
	public Protagonist buildAntagonist(String characterClass, String characterName);
}
