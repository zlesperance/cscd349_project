package cscd349_project;

public class Slime extends Antagonist {
	
	public Slime() {
		super("Slime", "A common monster that gets in the way of many a wyverian journey.", "STR:2,DEX:1,INT:1,VIT:3,AGI:1,LUK:1");
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		attack(enemies.selectRandomLivingCharacter());
	}

	@Override
	public void attack(Character foe) {
		foe.receiveDamage((int)(this.getStrength() * 1.5));
	}

}
