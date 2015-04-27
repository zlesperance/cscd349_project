package cscd349_project;

public abstract class Protagonist extends Character {
	private String name;
	// private Weapon weapon;
	// private Offhand offhand;
	

	protected Protagonist(String name, String skills) {
		super(skills);
		this.name = name;
	}

	@Override
	public int getHealthModifier() {
		return 0;
	}


	@Override
	public abstract void attack(Character foe);
	
	protected boolean isValidTarget(Character foe) {
		return !(foe instanceof Protagonist);
	}

}
