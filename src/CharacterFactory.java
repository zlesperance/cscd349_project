
public interface CharacterFactory {
	public Protagonist buildProtagonist(String characterClass, String characterName);
	public Antagonist buildAntagonist(String characterClass, String characterName);
	public Antagonist buildAntagonist(String characterClass);
}
