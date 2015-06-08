
public class Anarchist extends Antagonist {
	private Game game;
	private static final int WIRE_ID = 23;
	private static final int POTION_ID = 1;
	private static final int SWORD_ID = 7;
	private static final int BOW_ID = 14;
	private static final int BOLT_ID = 36;
	private static ItemDrops defaultDropTable;

	public Anarchist() {
		this("Anarchist");
	}
	
	public Anarchist(String name) {
		super(name
				, "A rowdy punk that don't play by no rules."
				, "STR:4,DEX:2,INT:0,VIT:3,AGI:2,LUK:1");
		this.game = Game.getInstance();
		if (Anarchist.defaultDropTable == null) {
			Anarchist.setDefaultDrops();
		}
		this.setDropTable(Anarchist.defaultDropTable);
	}
	
	private static void setDefaultDrops() {
		Anarchist.defaultDropTable = new ItemDrops();
		Anarchist.defaultDropTable.add(WIRE_ID, .5);
		Anarchist.defaultDropTable.add(POTION_ID, .15);
		Anarchist.defaultDropTable.add(SWORD_ID, .1);
		Anarchist.defaultDropTable.add(BOW_ID, .1);
		Anarchist.defaultDropTable.add(BOLT_ID, .15);
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
