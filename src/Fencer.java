
public class Fencer extends Protagonist {
	private boolean isBlocking;
	private int parryCount;
	private String[] actions;
	private Game game;
	
	public Fencer(String name) {
		super(name, "STR:2,DEX:4,INT:1,VIT:3,AGI:4,LUK:3");
		actions = new String[5];
		actions[0] = "Attack";
		actions[1] = "Parry";
		actions[2] = "Reposte";
		actions[3] = "Use Item";
		actions[4] = "Pass";
		parryCount = 0;
		this.game = Game.getInstance();
	}

	@Override
	public int getHealthModifier() {
		return 0;
	}

	@Override
	public int getStrengthModifier() {
		return (int) (this.skills.getStrength() * 0.1);
	}

	@Override
	public int getDexterityModifier() {
		return (int) (this.skills.getDexterity() * 0.25);
	}

	@Override
	public int getIntelligenceModifier() {
		return 0;
	}

	@Override
	public int getVitalityModifier() {
		return (int) -(this.skills.getVitality() * 0.1);
	}

	@Override
	public int getAgilityModifier() {
		return (int) (this.skills.getAgility() * 0.25);
	}

	@Override
	public int getLuckModifier() {
		return (int) (this.skills.getLuck() * 0.1);
	}
	
	private int getAttackEnergy() {
		return 2;
	}
	
	private int getBlockEnergy() {
		return 1;
	}
	
	private int getSpecialEnergy() {
		return 3;
	}

	@Override
	public void attack(Character foe) {
		if (!this.canDoAction(getBlockEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getAttackEnergy());
		this.game.report(toString() + " lunges at " + foe.toString() + "...");
		
		this.parryCount = 0;
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 25) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100)) {
			this.game.report(toString() + "'s attack missed!");
		} else {
			int baseDmg = (int) (getDexterity() + ((getStrength() + this.weapon.getAtk()) / 2));
			int damageRangeHigh = Math.min(10, getDexterity());
			int damageRangeLow = -Math.max(0, foe.getAgility());
			double roll = Math.max(0, Math.min(100, this.game.nextRandom() + (getLuck() / 100)));
			int actualDamage = Math.max(0, baseDmg + (int) (damageRangeLow + ((damageRangeHigh - damageRangeLow) * roll)));
			foe.receiveDamage(actualDamage);
		}
	}
	
	private void block() {
		if (!this.canDoAction(getBlockEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getBlockEnergy());
		this.isBlocking = true;
		this.game.report(getName() + " prepares to parry an attack");
	}
	
	private void specialAttack(Character foe) {
		if (!this.canDoAction(getSpecialEnergy()))
			throw new NotEnoughEnergyException();

		this.spendEnergy(getSpecialEnergy());
		this.game.report(toString() + " repostes at " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 50) + getLuck()) - ((foe.getAgility() * 15) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100)) {
			this.game.report(toString() + "'s repost missed!");
		} else {
			int baseDmg = (int) (getDexterity() * .8 + (getStrength() * .4));
			baseDmg *= (1 + (this.parryCount * .5));
			int damageRangeHigh = Math.min(10, getDexterity());
			int damageRangeLow = -Math.max(0, foe.getAgility());
			double roll = Math.max(0, Math.min(100, this.game.nextRandom() + (getLuck() / 100)));
			int actualDamage = Math.max(0, baseDmg + (int) (damageRangeLow + ((damageRangeHigh - damageRangeLow) * roll)));
			foe.receiveDamage(actualDamage);
		}
		
		this.parryCount = 0;
	}
	
	@Override
	public void receiveDamage(int damage) {
		if (isBlocking) {
			int blockingPower = (int) (((getDexterity()  * .4) + (getStrength() * .6)) * 4);
			int newDamage = Math.max(damage - blockingPower, 0);
			this.game.report(getName() + " parried " + (damage - newDamage) + " damage!");
			receiveDirectDamage(newDamage);
			this.parryCount++;
			
			if (!this.canDoAction(getBlockEnergy())) {
				this.isBlocking = false;
				this.game.report(getName() + " does not have enough energy to parry again!");
			} else {
				this.spendEnergy(getBlockEnergy());
			}
		} else {
			this.parryCount = 0;
			super.receiveDamage(damage);
		}
	}

	@Override
	public String[] getActions() {
		String[] actionsWithEnergy = new String[this.actions.length];
		
		actionsWithEnergy[0] = this.actions[0] + " (" + getAttackEnergy() + " EP)";
		actionsWithEnergy[1] = this.actions[1] + " (" + getBlockEnergy() + " EP)";
		actionsWithEnergy[2] = this.actions[2] + " (" + getSpecialEnergy() + " EP)";
		actionsWithEnergy[3] = this.actions[3] + " (" + getItemEnergy() + " EP)";
		actionsWithEnergy[4] = this.actions[4];
		
		return actionsWithEnergy;
	}
	
	@Override
	public void beginTurnHook() {
		this.isBlocking = false;		
	}

	@Override
	public void performAction(int index, Party allies, Party enemies,
			Engagement engagement) {
		if (index == 0) {
			Character foe = enemies.selectCharacter(new LivingCharacterTester());
			attack(foe);
		} else if (index == 1) {
			block();
		} else if (index == 2) {
			Character foe = enemies.selectCharacter(new LivingCharacterTester());			
			specialAttack(foe);
		} else if (index == 3) {
			useItem();
		}
	}

	@Override
	public boolean selectionStopsAction(int index) {
		return (index == 1 || index == 4);
	}

	@Override
	public boolean canPerformAnyAction() {
		return (this.canDoAction(getAttackEnergy()) || this.canDoAction(getSpecialEnergy()) || this.canDoAction(getBlockEnergy()) || this.canDoAction(getItemEnergy()));
	}

	@Override
	public String toString() {
		return getName();
	}

}
