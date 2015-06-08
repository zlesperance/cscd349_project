
public class Newt extends Antagonist {
	private Game game;
	private static final int EYE_ID = 20;
	private static final int POTION_ID = 0;
	private static ItemDrops defaultDropTable;
	
	public Newt() {
		this("Giant Newt");
	}
	
	public Newt(String name) {
		super(name
				, "A witch's experiment gone horribly wrong."
				, "STR:2,DEX:3,INT:1,VIT:4,AGI:3,LUK:1");
		this.game = Game.getInstance();
		if (Newt.defaultDropTable == null) {
			Newt.setDefaultDrops();
		}
		this.setDropTable(Newt.defaultDropTable);
	}
	
	private static void setDefaultDrops() {
		Newt.defaultDropTable = new ItemDrops();
		Newt.defaultDropTable.add(EYE_ID, .8);
		Newt.defaultDropTable.add(POTION_ID, .2);
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		attack(enemies.selectRandomLivingCharacter());
	}

	@Override
	public void attack(Character foe) {
		this.game.report(toString() + " bites at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 20) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100.0)) {
			this.game.report("but misses!");
		} else {
			foe.receiveDamage((int)(this.getStrength() * 1.5));
		}
	}

}
