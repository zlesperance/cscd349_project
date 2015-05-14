public class Fighter extends Protagonist {
	private boolean isBlocking;
	private String[] actions;
	
	public Fighter(String name) {
		super(name, "STR:4,DEX:3,INT:1,VIT:4,AGI:2,LUK:3");
		actions = new String[5];
		actions[0] = "Attack";
		actions[1] = "Whirlwind Strike";
		actions[2] = "Block";
		actions[3] = "Use Item";
		actions[4] = "Pass";
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
		return (int) -(this.skills.getIntelligence() * 0.25);
	}

	@Override
	public int getVitalityModifier() {
		return (int) (this.skills.getVitality() * 0.5);
	}

	@Override
	public int getAgilityModifier() {
		return (int) -(this.skills.getAgility() * 0.1);
	}

	@Override
	public int getLuckModifier() {
		return 0;
	}

	@Override
	public void attack(Character foe) throws NotEnoughEnergyException {
		if (this.energy < getAttackEnergy())
			throw new NotEnoughEnergyException();
		
		this.energy -= getAttackEnergy();
		Game.report(toString() + " swings their weapon at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 25) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (Game.nextRandom() > (hitChance / 100)) {
			Game.report(toString() + "'s attack missed!");
		} else {
			int baseDmg = (int) ((getStrength() + this.weapon.getAtk()) * 1.5);
			int damageRangeHigh = Math.min(10, getDexterity());
			int damageRangeLow = -Math.max(0, foe.getAgility());
			double roll = Math.max(0, Math.min(100, Game.nextRandom() + (getLuck() / 100)));
			int actualDamage = Math.max(0, baseDmg + (int) (damageRangeLow + ((damageRangeHigh - damageRangeLow) * roll)));
			foe.receiveDamage(actualDamage);
		}
	}
	
	private void specialAttack(Party foes) {
		if (this.energy < getSpecialEnergy())
			throw new NotEnoughEnergyException();
		
		this.energy -= getSpecialEnergy();
	}
	
	private void block() {
		if (this.energy < getBlockEnergy())
			throw new NotEnoughEnergyException();
		
		this.energy -= getBlockEnergy();
		this.isBlocking = true;
		Game.report(getName() + " prepares to block an attack");
	}
	
	private int getAttackEnergy() {
		return Math.max(6 - (this.getStrength() / 2), 2);
	}
	
	private int getBlockEnergy() {
		return Math.max(4 - (this.getVitality() / 4), 1);
	}
	
	private int getSpecialEnergy() {
		return Math.max(10 - (((this.getStrength() / 2) + (this.getDexterity() / 4) + (this.getVitality() / 4)) / 8), 4);
	}
	
	public void receiveDamage(int damage) {
		if (isBlocking) {
			int blockingPower = (int) (((getVitality()  * .6) + (getStrength() * .4)) * 4);
			int newDamage = Math.max(damage - blockingPower, 0);
			Game.report(getName() + " blocked " + (damage - newDamage) + " damage!");
			receiveDirectDamage(newDamage);
			
			if (this.energy < getBlockEnergy()) {
				this.isBlocking = false;
				Game.report(getName() + " does not have enough energy to block again!");
			} else {
				this.energy -= getBlockEnergy();
			}
		} else {
			super.receiveDamage(damage);
		}
	}
	
	@Override
	public String[] getActions() {
		String[] actionsWithEnergy = new String[this.actions.length];
		
		actionsWithEnergy[0] = this.actions[0] + " (" + getAttackEnergy() + " EP)";
		actionsWithEnergy[1] = this.actions[1] + " (" + getSpecialEnergy() + " EP)";
		actionsWithEnergy[2] = this.actions[2] + " (" + getBlockEnergy() + " EP)";
		actionsWithEnergy[3] = this.actions[3] + " (" + getItemEnergy() + " EP)";
		actionsWithEnergy[4] = this.actions[4];
		
		return actionsWithEnergy;
	}
	
	@Override
	protected void beginTurnHook() {
		this.isBlocking = false;
	}
	
	@Override
	public void performAction(int index, Party allies, Party enemies, Engagement engagement) throws NotEnoughEnergyException {
		if (index == 0) {
			Character foe = enemies.selectCharacter(new LivingCharacterTester());
			attack(foe);
		} else if (index == 1) {
			specialAttack(enemies);
		} else if (index == 2) {
			block();
		} else if (index == 3) {
			useItem();
		}
	}
	
	@Override
	public boolean selectionStopsAction(int index) {
		return (index == 2 || index == 4);
	}
	
	@Override
	public boolean canPerformAnyAction() {
		return (this.energy >= getAttackEnergy() || this.energy >= getSpecialEnergy() || this.energy >= getBlockEnergy() || this.energy >= getItemUseEnergy());
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
