package cscd349_project;

import java.util.regex.Pattern;

public class SkillSet {
	private int strength;
	private int agility;
	private int vitality;
	private int intelligence;
	private int dexterity;
	private int luck;
	
	public SkillSet(int strength, int agility, int vitality, int intelligence, int dexterity, int luck) {
		this.strength = strength;
		this.agility = agility;
		this.vitality = vitality;
		this.intelligence = intelligence;
		this.dexterity = dexterity;
		this.luck = luck;
	}
	
	public int getBaseHP(int healthModifier) {
		return vitality * 10;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getAgility() {
		return this.agility;
	}
	
	public int getVitality() {
		return this.vitality;
	}
	
	public int getIntelligence() {
		return this.intelligence;
	}
	
	public int getDexterity() {
		return this.dexterity;
	}
	
	public int getLuck() {
		return this.luck;
	}
	
	public static SkillSet buildSkills(String skills) {
		String verificationRegex = "(STR|AGI|VIT|INT|DEX|LUK):[0-9]+(,(STR|AGI|VIT|INT|DEX|LUK):[0-9]){5}";
		if (!Pattern.compile(verificationRegex).matcher(skills).matches())
			throw new IllegalArgumentException("Skill String is invalid: " + skills);
		
		String[] skillsSplit = skills.split(",");
		int str = 0,
			agi = 0,
			vit = 0,
			inl = 0,
			dex = 0,
			luk = 0;
		
		for (String skill : skillsSplit) {
			String[] skillSplit = skill.split(":");
			String skillName = skillSplit[0];
			int skillValue = Integer.parseInt(skillSplit[1]);
			
			if (skillName.equals("STR"))
				str = skillValue;
			else if (skillName.equals("AGI"))
				agi = skillValue;
			else if (skillName.equals("VIT"))
				vit = skillValue;
			else if (skillName.equals("INT"))
				inl = skillValue;
			else if (skillName.equals("DEX"))
				dex = skillValue;
			else if (skillName.equals("LUK"))
				luk = skillValue;
			else
				throw new RuntimeException("Invalid Skill Name: " + skillName);
		}
		
		return new SkillSet(str, agi, vit, inl, dex, luk);
	}
	
	public void raiseStrength() {
		this.strength++;
	}
	
	public void raiseAgility() {
		this.agility++;
	}
	
	public void raiseVitality() {
		this.vitality++;
	}
	
	public void raiseIntelligence() {
		this.intelligence++;
	}
	
	public void raiseLuck() {
		this.luck++;
	}
}
