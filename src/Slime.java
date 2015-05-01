public class Slime extends Antagonist {
	
	public Slime() {
		this("Slime");
	}
	
	public Slime(String name) {
		super(name
				, "A common monster that gets in the way of many a wyverian journey."
				, "STR:2,DEX:1,INT:1,VIT:3,AGI:1,LUK:1");
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		attack(enemies.selectRandomLivingCharacter());
	}

	@Override
	public void attack(Character foe) {
		Game.report(toString() + " lauches itself at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 20) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (Game.nextRandom() > (hitChance / 100.0)) {
			Game.report("but misses!");
		} else {
			foe.receiveDamage((int)(this.getStrength() * 1.5));
		}
	}

}
