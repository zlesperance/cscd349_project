public class Slime extends Antagonist {
	private Game game;
	private static final int GEL_ID = 18;
	private static final int POTION_ID = 0;
	private static ItemDrops defaultDropTable;
	
	public Slime() {
		this("Slime");
	}
	
	public Slime(String name) {
		super(name
				, "A common monster that gets in the way of many a wyverian journey."
				, "STR:2,DEX:1,INT:1,VIT:3,AGI:1,LUK:1");
		this.game = Game.getInstance();
		if (Slime.defaultDropTable == null) {
			Slime.setDefaultDrops();
		}
		this.setDropTable(Slime.defaultDropTable);
	}
	
	private static void setDefaultDrops() {
		Slime.defaultDropTable = new ItemDrops();
		Slime.defaultDropTable.add(GEL_ID, .8);
		Slime.defaultDropTable.add(POTION_ID, .2);		
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		attack(enemies.selectRandomLivingCharacter());
	}

	@Override
	public void attack(Character foe) {
		this.game.report(toString() + " lauches itself at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 20) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100.0)) {
			this.game.report("but misses!");
		} else {
			foe.receiveDamage((int)(this.getStrength() * 1.5));
		}
	}

}
