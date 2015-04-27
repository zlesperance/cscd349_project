package cscd349_project;

public abstract class Character {
	private int healthPoints;
	protected SkillSet skills;
	
	protected Character(String skills) {
		this.skills = SkillSet.buildSkills(skills);
		this.healthPoints = this.skills.getBaseHP(0);
	}
	
	public void receiveDamage(int damage) {
		if (tryDodge()) {
			// report to game logic that dodge succeeded
			return;
		}
		
		if (tryBlock()) {
			damage = damage; // Change to reflect block damage reduction
			// report to game logic successful block
		}
		
		this.healthPoints -= damage;
		if (this.healthPoints < 0)
			this.die();
	}
	
	private boolean tryDodge() {
		int agility = this.skills.getAgility() + this.getAgilityModifier();
		int luck = this.skills.getLuck() + this.getLuckModifier();
		
		
		
		return false;
	}
	
	private boolean tryBlock() {
		int vitality = this.skills.getVitality() + this.getVitalityModifier();
		int strength = this.skills.getStrength() + this.getStrengthModifier();
		
		return false;
	}
	
	public void healDamage(int healAmount) {
		this.healthPoints = Math.min(this.healthPoints + healAmount, this.skills.getBaseHP(this.getHealthModifier()));
	}
	
	public abstract int getHealthModifier();
	
	public abstract int getStrengthModifier();
	
	public abstract int getAgilityModifier();
	
	public abstract int getLuckModifier();
	
	public abstract int getVitalityModifier();
	
	private void die() {
		// Do something with game logic
	}
	
	public abstract void attack(Character foe);
	
}
