
public class Goblin extends Antagonist {
	private Game game;
	private static final int WIRE_ID = 23;
	private static final int POTION_ID = 1;
	private static final int SWORD_ID = 7;
	private static final int BOW_ID = 14;
	private static final int BOLT_ID = 36;
	private static ItemDrops defaultDropTable;

	public Goblin() {
		this("Anarchist");
	}
	
	public Goblin(String name) {
		super(name
				, "A rowdy punk that don't play by no rules."
				, "STR:4,DEX:2,INT:0,VIT:3,AGI:2,LUK:1");
		this.game = Game.getInstance();
		if (Goblin.defaultDropTable == null) {
			Goblin.setDefaultDrops();
		}
		this.setDropTable(Goblin.defaultDropTable);
	}
	
	private static void setDefaultDrops() {
		Goblin.defaultDropTable = new ItemDrops();
		Goblin.defaultDropTable.add(WIRE_ID, .5);
		Goblin.defaultDropTable.add(POTION_ID, .15);
		Goblin.defaultDropTable.add(SWORD_ID, .1);
		Goblin.defaultDropTable.add(BOW_ID, .1);
		Goblin.defaultDropTable.add(BOLT_ID, .15);
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		attack(enemies.selectRandomLivingCharacter());
	}

	@Override
	public void attack(Character foe) {
		this.game.report(toString() + " swings wildly at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 20) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100.0)) {
			this.game.report("but misses!");
		} else {
			foe.receiveDamage((int)(this.getStrength() * 1.5));
		}
	}

}
