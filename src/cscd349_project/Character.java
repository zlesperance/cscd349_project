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
		this.healthPoints = Math.min(this.healthPoints + healAmount, this.getBaseHP());
	}
	
	public int getBaseHP() {
		return this.skills.getBaseHP(0);
	}
	
	public abstract int getStrength();
	
	public abstract int getAgility();
	
	public abstract int getVitality();
	
	public abstract int getDexterity();
	
	public abstract int getIntelligence();
	
	public abstract int getLuck();
	
	private void die() {
		// Do something with game logic
	}
	
	public abstract void attack(Character foe);
	
}
