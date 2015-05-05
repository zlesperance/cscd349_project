public abstract class Protagonist extends Character {
	private String name;
	// protected Weapon weapon;
	// protected Offhand offhand;
	protected int energy;

	protected Protagonist(String name, String skills) {
		super(skills);
		this.name = name;
	}
	
	protected void refresh() {
		this.energy = this.getVitality();
	}
	
	public int getItemEnergy() {
		return 2;
	}
	
	public int getBaseHP() {
		return this.skills.getBaseHP() + this.getHealthModifier();
	}
	
	public int getStrength() {
		return Math.max(1, this.skills.getStrength() + this.getStrengthModifier());
	}
	
	public int getDexterity() {
		return Math.max(1, this.skills.getDexterity() + this.getDexterityModifier());
	}
	
	public int getIntelligence() {
		return Math.max(1, this.skills.getIntelligence() + this.getIntelligenceModifier());
	}
	
	public int getAgility() {
		return Math.max(1, this.skills.getAgility() + this.getAgilityModifier());
	}
	
	public int getVitality() {
		return Math.max(1, this.skills.getVitality() + this.getVitalityModifier());
	}
	
	public int getLuck() {
		return this.skills.getLuck() + this.getLuckModifier();
	}

	public abstract int getHealthModifier();

	public abstract int getStrengthModifier();
	
	public abstract int getDexterityModifier();
	
	public abstract int getIntelligenceModifier();
	
	public abstract int getVitalityModifier();
	
	public abstract int getAgilityModifier();
	
	public abstract int getLuckModifier();

	@Override
	public abstract void attack(Character foe);
	
	protected boolean isValidTarget(Character foe) {
		return !(foe instanceof Protagonist);
	}
	
	public void levelUp() {
		// prompt for what to level up & level up skills
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public final void selectAction(Party allies, Party enemies, Engagement engagement) {
		boolean endTurn = false;
		refresh();
		beginTurnHook();
		
		do {
			Game.report("What will " + toString() + " do? [" + getHP() + " HP, " + this.energy + " EP]");
			int selection = Game.makeSelection(getActions());
			try {
				performAction(selection, allies, enemies, engagement);
				
				if (enemies.isDefeated() || selectionStopsAction(selection) || !canPerformAnyAction())
					endTurn = true;
			} catch (NotEnoughEnergyException e) {
				Game.report(e.getMessage());
			}
		} while (!endTurn);
		
		endTurnHook();
	}
	
	public int getItemUseEnergy() {
		return 2;
	}
	
	public void useItem() {
		// TODO: insert use item functionality
	}
	
	protected void beginTurnHook() {}
	
	public abstract String[] getActions();
	
	public abstract void performAction(int index, Party allies, Party enemies, Engagement engagement);
	
	public abstract boolean selectionStopsAction(int index);
	
	public abstract boolean canPerformAnyAction();

	protected void endTurnHook() {}
}
