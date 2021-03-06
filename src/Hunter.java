
public class Hunter extends Protagonist {
	private boolean isAiming;
	private Character target;
	private String[] actions;
	
	public Hunter(String name) {
		super(name, "STR:1,DEX:3,INT:2,VIT:2,AGI:5,LUK:3");
		actions = new String[5];
		actions[0] = "Fire";
		actions[1] = "Take Aim";
		actions[2] = "Piercing Shot";
		actions[3] = "Use Item";
		actions[4] = "Pass";
		
		this.isAiming = false;
		this.target = null;
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
		return (int) (this.skills.getDexterity() * 0.25);
	}

	@Override
	public int getIntelligenceModifier() {
		return (int) (this.skills.getIntelligence() * 0.1);
	}

	@Override
	public int getVitalityModifier() {
		return (int) -(this.skills.getVitality() * 0.5);
	}

	@Override
	public int getAgilityModifier() {
		return (int) (this.skills.getAgility() * 0.5);
	}

	@Override
	public int getLuckModifier() {
		return (int) (this.skills.getLuck() * 0.1);
	}
	
	private int getAttackEnergy() {
		return 0;
	}
	
	private int getAimEnergy() {
		return 0;
	}
	
	private int getSpecialEnergy() {
		return 0;
	}

	@Override
	public void attack(Character foe) {
		
	}
	
	private void aim(Character foe) {
		this.spendEnergy(getAimEnergy());
		
		this.target = foe;
		this.isAiming = true;
	}
	
	private void specialAttack(Character foe) {
		
	}

	@Override
	public String[] getActions() {
		String[] actionsWithEnergy = new String[this.actions.length];
		
		actionsWithEnergy[0] = this.actions[0] + " (" + getAttackEnergy() + " EP)";
		actionsWithEnergy[1] = this.actions[1] + " (" + getAimEnergy() + " EP)";
		actionsWithEnergy[2] = this.actions[2] + " (" + getSpecialEnergy() + " EP)";
		actionsWithEnergy[3] = this.actions[3] + " (" + getItemEnergy() + " EP)";
		actionsWithEnergy[4] = this.actions[4];
		
		return this.actions;
	}

	@Override
	public void performAction(int index, Party allies, Party enemies,
			Engagement engagement) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean selectionStopsAction(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canPerformAnyAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
