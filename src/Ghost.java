
public class Ghost extends Antagonist {
	private Game game;
	private static final int PLASM_ID = 31;
	private static final int SCROLL_ID = 3;
	private static final int STAFF_ID = 10;
	private static ItemDrops defaultDropTable;
	
	public Ghost() {
		this("Ghost");
	}
	
	public Ghost(String name) {
		super(name
				, "A spooky ghost!"
				, "STR:1,DEX:1,INT:3,VIT:5,AGI:3,LUK:2");
		this.game = Game.getInstance();
		if (Ghost.defaultDropTable == null) {
			Ghost.setDefaultDrops();
		}
		this.setDropTable(Ghost.defaultDropTable);
	}
	
	private static void setDefaultDrops() {
		Ghost.defaultDropTable = new ItemDrops();
		Ghost.defaultDropTable.add(PLASM_ID, .7);
		Ghost.defaultDropTable.add(SCROLL_ID, .2);
		Ghost.defaultDropTable.add(STAFF_ID, .1);
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		attack(enemies.selectRandomLivingCharacter());
	}

	@Override
	public void attack(Character foe) {
		this.game.report(toString() + " tries to spook " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 20) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100.0)) {
			this.game.report("but misses!");
		} else {
			foe.receiveDamage((int)(this.getIntelligence() * 1.5));
		}
	}

}
