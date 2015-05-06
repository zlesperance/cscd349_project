
public class Mancer extends Protagonist {
	
	public Mancer(String name) {
		super(name, "STR:1,DEX:2,INT:4,VIT:2,AGI:3,LUK:3");
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVitalityModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAgilityModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLuckModifier() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void attack(Character foe) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getActions() {
		// TODO Auto-generated method stub
		return null;
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
