package cscd349_project;

public abstract class Character {
	private int healthPoints;
	protected SkillSet skills;
	
	protected Character(String skills) {
		this.skills = SkillSet.buildSkills(skills);
		this.healthPoints = this.skills.getBaseHP();
	}
	
	public abstract void selectAction(Party allies, Party enemies, Engagement engagement);
	
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
	
	protected void receiveDirectDamage(int damage) {
		this.healthPoints -= damage;
		if (this.healthPoints < 0)
			this.die();
	}
	
	private boolean tryDodge() {
		int agility = this.skills.getAgility();
		int luck = this.skills.getLuck();
		
		
		
		return false;
	}
	
	private boolean tryBlock() {
		int vitality = this.skills.getVitality();
		int strength = this.skills.getStrength();
		
		return false;
	}
	
	public void healDamage(int healAmount) {
		this.healthPoints = Math.min(this.healthPoints + healAmount, this.getBaseHP());
	}
	
	public int getBaseHP() {
		return this.skills.getBaseHP();
	}
	
	public abstract int getStrength();
	
	public abstract int getAgility();
	
	public abstract int getVitality();
	
	public abstract int getDexterity();
	
	public abstract int getIntelligence();
	
	public abstract int getLuck();
	
	public int getSpeed() {
		return (int)((this.getDexterity() * .6) + (this.getAgility() * .4));
	}
	
	private void die() {
		// Do something with game logic
	}
	
	public boolean isDead() {
		return this.healthPoints <= 0;
	}
	
	public abstract void attack(Character foe);
	
	public abstract String toString();
	
}
