package cscd349_project;

public class Fighter extends Protagonist {

	@Override
	protected void refresh() {
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void selectAction(Party allies, Party enemies, Engagement engagement) {
		// TODO Auto-generated method stub

	}

}
