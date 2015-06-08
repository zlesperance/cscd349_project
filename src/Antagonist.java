public abstract class Antagonist extends Character {
	private String name;
	private String description;
	private ItemDrops dropTable;
	private int KEY_ID = 39;
	
	public Antagonist(String name, String description, String skills) {
		super(skills);
		this.name = name;
		this.description = description;
	}

	@Override
	public int getStrength() {
		return this.skills.getStrength();
	}

	@Override
	public int getAgility() {
		return this.skills.getAgility();
	}

	@Override
	public int getVitality() {
		return this.skills.getVitality();
	}

	@Override
	public int getDexterity() {
		return this.skills.getDexterity();
	}

	@Override
	public int getIntelligence() {
		return this.skills.getIntelligence();
	}

	@Override
	public int getLuck() {
		return this.skills.getLuck();
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	public String examine() {
		return this.description;
	}
	
	protected void setDropTable(ItemDrops dropTable) {
		this.dropTable = dropTable;
	}
	
	public void makeKeyBearer() {
		this.dropTable = new ItemDrops();
		this.dropTable.add(KEY_ID, 1.0);
	}
	
	public Item loot() {
		return dropTable.getItem();
	}

}
