
public class Mancer extends Protagonist {
	private int imps;
	private String[] actions;
	private Game game;
	
	public Mancer(String name) {
		super(name, "STR:1,DEX:2,INT:4,VIT:2,AGI:3,LUK:3");
		actions = new String[5];
		actions[0] = "Pyromancy";
		actions[1] = "Restomancy";
		actions[2] = "Summon Imp";
		actions[3] = "Use Item";
		actions[4] = "Pass";
		this.game = Game.getInstance();
	}

	@Override
	public int getHealthModifier() {
		return 0;
	}

	@Override
	public int getStrengthModifier() {
		return (int) -(this.skills.getStrength() * 0.25);
	}

	@Override
	public int getDexterityModifier() {
		return (int) -(this.skills.getStrength() * 0.25);
	}

	@Override
	public int getIntelligenceModifier() {
		return (int) (this.skills.getIntelligence() * 0.5);
	}

	@Override
	public int getVitalityModifier() {
		return (int) -(this.skills.getIntelligence() * 0.1);
	}

	@Override
	public int getAgilityModifier() {
		return (int) (this.skills.getIntelligence() * 0.2);
	}

	@Override
	public int getLuckModifier() {
		return (int) (this.skills.getLuck() * 0.1);
	}

	@Override
	public void attack(Character foe) {
		if (!this.canDoAction(getAttackEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getAttackEnergy());
		this.game.report(toString() + " sends a fireball rocketing towards " + foe.toString() + "...");
		
		int accuracy = ((getDexterity() * 100) + getLuck()) - ((foe.getAgility() * 25) + getLuck()); 
		int hitChance = Math.min(100, Math.max(50, accuracy));
		if (this.game.nextRandom() > (hitChance / 100)) {
			this.game.report(toString() + "'s fireball missed!");
		} else {
			int baseDmg = (int) ((getIntelligence() + this.weapon.getAtk()) * 1.5);
			int damageRangeHigh = Math.min(10, getLuck());
			int damageRangeLow = -Math.max(0, foe.getAgility());
			double roll = Math.max(0, Math.min(100, this.game.nextRandom() + (getLuck() / 100)));
			int actualDamage = Math.max(0, baseDmg + (int) (damageRangeLow + ((damageRangeHigh - damageRangeLow) * roll)));
			foe.receiveDamage(actualDamage);
		}
	}
	
	private int getAttackEnergy() {
		return Math.max(6 - (this.getIntelligence() / 3), 2);
	}
	
	private void healAllies(Party allies) {
		if (!this.canDoAction(getHealEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getHealEnergy());
		
		int healAmount = (int) (this.getIntelligence() * 1.25);
		
		for (Character ally : allies) {
			ally.healDamage(healAmount);
		}
	}
	
	private int getHealEnergy() {
		return Math.max(10 - (this.getIntelligence() / 4), 4);
	}
	
	private void summonImp() {
		if (!this.canDoAction(getAttackEnergy()))
			throw new NotEnoughEnergyException();
		
		this.spendEnergy(getAttackEnergy());
		
		game.report(this.getName() + " summons a bloodthirsty imp to defend them!");
		this.imps++;
	}
	
	private int getSummonEnergy() {
		return Math.max(4 - (this.getIntelligence() / 4) + (this.getVitality() / 4), 1);
	}
	
	@Override
	protected void beginTurnHook() {
		if (this.imps > 0) {
			game.report(this.getName() + "'s imps grow bored...");
			for (int i = 0; i < this.imps; i++) {
				if (game.nextRandom() < (.5 + (this.getLuck() / 100))) {
					game.report("An imp decides to relieve its boredom by biting " + this.getName() + "...");
					this.receiveDirectDamage(2);
				}
			}
		}
	}
	
	@Override
	public void receiveDamage(int damage) {
		if (this.imps > 0) {
			game.report("One of " + this.getName() + "'s imps leaps in front of the attack, destroying the imp, but protecting its master!");
			this.imps--;
		} else {
			super.receiveDamage(damage);
		}
	}

	@Override
	public String[] getActions() {
		String[] actionsWithEnergy = new String[this.actions.length];
		
		actionsWithEnergy[0] = this.actions[0] + " (" + getAttackEnergy() + " EP)";
		actionsWithEnergy[1] = this.actions[1] + " (" + getHealEnergy() + " EP)";
		actionsWithEnergy[2] = this.actions[2] + " (" + getSummonEnergy() + " EP)";
		actionsWithEnergy[3] = this.actions[3] + " (" + getItemEnergy() + " EP)";
		actionsWithEnergy[4] = this.actions[4];
		
		return actionsWithEnergy;
	}

	@Override
	protected void performAction(int index, Party allies, Party enemies,
			Engagement engagement) {
		if (index == 0) {
			Character foe = enemies.selectCharacter(new LivingCharacterTester());
			attack(foe);
		} else if (index == 1) {
			healAllies(allies);
		} else if (index == 2) {
			summonImp();
		} else if (index == 3) {
			useItem();
		}
	}

	@Override
	public boolean selectionStopsAction(int index) {
		return (index == 4);
	}

	@Override
	public boolean canPerformAnyAction() {
		return (this.canDoAction(getAttackEnergy()) || this.canDoAction(getHealEnergy()) || this.canDoAction(getSummonEnergy()) || this.canDoAction(getItemEnergy()));
	}

	@Override
	public String toString() {
		return getName();
	}

}
