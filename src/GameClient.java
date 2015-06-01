
public interface GameClient {
	public int makeSelection(String... options);
	
	public Item openInventory(Inventory inventory);
	
	public Party openCharacterSelect();
	
	public void openMap(Maze map);
	
	public void openEngagement(Engagement engagement);	
	
	public void report(String string);
	
	public void end();
}
