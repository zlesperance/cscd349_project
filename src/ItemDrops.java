import java.util.ArrayList;


public class ItemDrops {
	private ArrayList<ItemDrop> drops;
	private Game game;
	
	public ItemDrops() {
		drops = new ArrayList<ItemDrop>();
		game = Game.getInstance();
	}
	
	public void add(int itemID, double dropRate) {
		if (dropRate < 0 || dropRate > 1.0)
			throw new IllegalArgumentException("Error: Drop Rate must be less than or equal to 1");
		drops.add(new ItemDrop(itemID, dropRate));
	}
	
	public Item getItem() {
		if (drops.size() == 0)
			throw new IllegalStateException("Drop Table is empty!");
		
		double result = game.nextRandom();
		
		double sum = 0;
		for (ItemDrop drop : drops) {
			if (result < sum + drop.getDropRate())
				return game.getItemByID(drop.getItemID());
			sum += drop.getDropRate();
		}
		
		return game.getItemByID(drops.get(drops.size() - 1).getItemID());
	}
}
