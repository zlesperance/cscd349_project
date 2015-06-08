
public interface CharacterFactory {
	public Protagonist buildProtagonist(String characterClass, String characterName);
	public Antagonist buildAntagonist(String characterClass, boolean key);
	public Antagonist buildAntagonist(String characterClass);
}
