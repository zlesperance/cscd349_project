package cscd349_project;

public class Fighter extends Protagonist {
	private boolean isBlocking;
	
	public Fighter(String name) {
		super(name, "STR:4,DEX:3,INT:1,VIT:4,AGI:2,LUK:3");
	}

	@Override
	public int getHealthModifier() {
		return (int) (this.skills.getBaseHP() * 0.1);
	}

	@Override
	public int getStrengthModifier() {
		return (int) (this.skills.getStrength() * 0.25);
	}

	@Override
	public int getDexterityModifier() {
		return (int) (this.skills.getDexterity() * 0.1);
	}

	@Override
	public int getIntelligenceModifier() {
		return (int) -(this.skills.getDexterity() * 0.25);
	}

	@Override
	public int getVitalityModifier() {
		return (int) (this.skills.getBaseHP() * 0.5);
	}

	@Override
	public int getAgilityModifier() {
		return (int) -(this.skills.getBaseHP() * 0.1);
	}

	@Override
	public int getLuckModifier() {
		return 0;
	}

	@Override
	public void attack(Character foe) {
		
	}
	
	private int getAttackEnergy() {
		return 6 - (this.getStrength() / 2);
	}
	
	private int getBlockEnergy() {
		return 4 - (this.getVitality() / 4);
	}
	
	private int getSpecialEnergy() {
		return 10 - (((this.getStrength() / 2) + (this.getDexterity() / 4) + (this.getVitality() / 4)) / 8);
	}
	
	public void receiveDamage(int damage) {
		if (isBlocking) {
			int blockingPower = (int) (((getVitality()  * .6) + (getStrength() * .4)) * 4);
			int newDamage = Math.max(damage - blockingPower, 0);
			Game.report(getName() + " blocked " + (damage - newDamage) + "damage!");
			receiveDirectDamage(damage);
		} else {
			super.receiveDamage(damage);
		}
	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		boolean endTurn = false;
		do {
			int selection = Game.makeSelection("Attack (" + getAttackEnergy() + " EP)", "Block (" + getBlockEnergy() + " EP)", "Whirlwind Strike (" + getSpecialEnergy() + " EP)", "Use Item", "Pass");
			if (selection == 0) {
				if (this.energy >= getAttackEnergy()) {
					// Select target then attack
				}
			}
			
			// if out of energy or pass, do this:
			endTurn = true;
		} while (!endTurn);
	}
	
	@Override
	public String toString() {
		return getName() + ", the Fighter";
	}

}
