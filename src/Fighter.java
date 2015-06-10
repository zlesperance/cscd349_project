public class Fighter extends Protagonist {
	private boolean isBlocking;
	private String[] actions;
	private Game game;
	
	public Fighter(String name) {
		super(name, "STR:4,DEX:3,INT:1,VIT:4,AGI:2,LUK:3");
		actions = new String[5];
		actions[0] = "Attack";
		actions[1] = "Whirlwind Strike";
		actions[2] = "Block";
		actions[3] = "Use Item";
		actions[4] = "Pass";
		this.game = Game.getInstance();
	}

	@Override
	protected int getHealthModifier() {
		return (int) (this.skills.getBaseHP() * 0.1);
	}

	@Override
	protected int getStrengthModifier() {
		return (int) (this.skills.getStrength() * 0.25);
	}

	@Override
	protected int getDexterityModifier() {
		return (int) (this.skills.getDexterity() * 0.1);
	}

	@Override
	protected int getIntelligenceModifier() {
		return (int) -(this.skills.getIntelligence() * 0.25);
	}

	@Override
	protected int getVitalityModifier() {
		return (int) (this.skills.getVitality() * 0.5);
	}

	@Override
	protected int getAgilityModifier() {
		return (int) -(this.skills.getAgility() * 0.1);
	}

	@Override
	protected int getLuckModifier() {
		return 0;
	}

	@Override
	public void attack(Character foe) throws NotEnoughEnergyException {
		if (!this.canDoAction(getAttackEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getAttackEnergy());
		this.game.report(toString() + " swings their weapon at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 25) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100)) {
			this.game.report(toString() + "'s attack missed!");
		} else {
			int baseDmg = (int) ((getStrength() + this.weapon.getAtk()) * 1.5);
			int damageRangeHigh = Math.min(10, getDexterity());
			int damageRangeLow = -Math.max(0, foe.getAgility());
			double roll = Math.max(0, Math.min(100, this.game.nextRandom() + (getLuck() / 100)));
			int actualDamage = Math.max(0, baseDmg + (int) (damageRangeLow + ((damageRangeHigh - damageRangeLow) * roll)));
			foe.receiveDamage(actualDamage);
		}
	}
	
	private void specialAttack(Party foes) {
		if (!this.canDoAction(getSpecialEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getSpecialEnergy());
		
		this.game.report(toString() + " does a wide, mighty swing at the enemies...");
		
		for (Character foe : foes) {
			int accuracy = ((getDexterity() * 45) + getLuck()) - ((foe.getAgility() * 20) + getLuck()); 
			int hitChance = Math.min(100, Math.max(50, accuracy));
			if (this.game.nextRandom() > (hitChance / 100)) {
				this.game.report(toString() + "'s swing missed " + foe.toString() + "!");
			} else {
				int baseDmg = (int) ((getStrength() + this.weapon.getAtk()) * 2);
				int damageRangeHigh = Math.min(10, getDexterity());
				int damageRangeLow = -Math.max(0, foe.getAgility());
				double roll = Math.max(0, Math.min(100, this.game.nextRandom() + (getLuck() / 100)));
				int actualDamage = Math.max(0, baseDmg + (int) (damageRangeLow + ((damageRangeHigh - damageRangeLow) * roll)));
				foe.receiveDamage(actualDamage);
			}
		}
	}
	
	private void block() {
		if (!this.canDoAction(getBlockEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getBlockEnergy());
		this.isBlocking = true;
		this.game.report(getName() + " prepares to block an attack");
	}
	
	private int getAttackEnergy() {
		return Math.max(6 - (this.getStrength() / 2), 2);
	}
	
	private int getBlockEnergy() {
		return Math.max(4 - (this.getVitality() / 4), 1);
	}
	
	private int getSpecialEnergy() {
		return Math.max(10 - (((this.getStrength() / 2) + (this.getDexterity() / 2) + (this.getVitality() / 4))), 4);
	}
	
	public void receiveDamage(int damage) {
		if (isBlocking) {
			int blockingPower = (int) (((getVitality()  * .6) + (getStrength() * .4)) * 4);
			int newDamage = Math.max(damage - blockingPower, 0);
			this.game.report(getName() + " blocked " + (damage - newDamage) + " damage!");
			receiveDirectDamage(newDamage);
			
			if (!this.canDoAction(getBlockEnergy())) {
				this.isBlocking = false;
				this.game.report(getName() + " does not have enough energy to block again!");
			} else {
				this.spendEnergy(getBlockEnergy());
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
	protected void performAction(int index, Party allies, Party enemies, Engagement engagement) throws NotEnoughEnergyException {
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
	protected boolean selectionStopsAction(int index) {
		return (index == 2 || index == 4);
	}
	
	@Override
	protected boolean canPerformAnyAction() {
		return (this.canDoAction(getAttackEnergy()) || this.canDoAction(getSpecialEnergy()) || this.canDoAction(getBlockEnergy()) || this.canDoAction(getItemEnergy()));
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
