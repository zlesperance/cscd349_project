public abstract class Protagonist extends Character {
	private String name;
	protected Weapon weapon;
	protected Shield offhand;
	private int energy;
	private Game game;

	protected Protagonist(String name, String skills) {
		super(skills);
		this.name = name;
		this.weapon = new Weapon();
		this.offhand = new Shield();
		this.game = Game.getInstance();
	}
	
	public void equipWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public void equipOffhand(Shield shield) {
		this.offhand = shield;
	}
	
	protected void refresh() {
		this.energy = this.getVitality();
	}
	
	protected void spendEnergy(int energy) {
		this.energy = Math.max(0, this.energy - energy);
	}
	
	protected boolean canDoAction(int energyRequirement) {
		return this.energy >= energyRequirement;
	}
	
	protected int getItemEnergy() {
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

	protected abstract int getHealthModifier();

	protected abstract int getStrengthModifier();
	
	protected abstract int getDexterityModifier();
	
	protected abstract int getIntelligenceModifier();
	
	protected abstract int getVitalityModifier();
	
	protected abstract int getAgilityModifier();
	
	protected abstract int getLuckModifier();

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
			this.game.report("What will " + toString() + " do? [" + getHP() + " HP, " + this.energy + " EP]");
			int selection = this.game.makeSelection(getActions());
			try {
				performAction(selection, allies, enemies, engagement);
				
				if (enemies.isDefeated() || selectionStopsAction(selection) || !canPerformAnyAction())
					endTurn = true;
			} catch (NotEnoughEnergyException e) {
				this.game.report(e.getMessage());
			}
		} while (!endTurn);
		
		endTurnHook();
	}
	
	protected void useItem() {
		if (!this.canDoAction(getItemEnergy()))
			throw new NotEnoughEnergyException();
		
		Item item = game.openInventory();
		if (item != null) {
			spendEnergy(this.getItemEnergy());
			item.use(game.getProtagonists());
		}
	}
	
	protected void beginTurnHook() {}
	
	protected abstract String[] getActions();
	
	protected abstract void performAction(int index, Party allies, Party enemies, Engagement engagement);
	
	protected abstract boolean selectionStopsAction(int index);
	
	protected abstract boolean canPerformAnyAction();

	protected void endTurnHook() {}
}
