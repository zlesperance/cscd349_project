import java.util.Iterator;

public class Engagement {
	private Party<Protagonist> partyA;
	private Party<Antagonist> partyB;
	private boolean ended;
	private boolean escapable;
	private Game game;
	
	public Engagement(Party<Protagonist> partyA, Party<Antagonist> partyB) {
		this(partyA, partyB, true);
	}
	
	public Engagement(Party<Protagonist> partyA, Party<Antagonist> partyB, boolean escapable) {
		this.partyA = partyA;
		this.partyB = partyB;
		this.escapable = escapable;
		this.game = Game.getInstance();
	}
	
	public void begin() {
		this.ended = false;
		Iterator<Protagonist> partyACharacters = partyA.iterator();
		Iterator<Antagonist> partyBCharacters = partyB.iterator();
		Character partyANext = partyACharacters.next(),
				partyBNext = partyBCharacters.next();
		
		while(!(ended || partyA.isDefeated() || partyB.isDefeated())) {
			while (partyANext != null && partyANext.isDead()) {
				if (partyACharacters.hasNext())
					partyANext = partyACharacters.next();
				else
					partyANext = null;
			}
			while (partyBNext != null && partyBNext.isDead()) {
				if (partyBCharacters.hasNext())
					partyBNext = partyBCharacters.next();
				else
					partyBNext = null;
			}
			
			if (partyANext == null && partyBNext == null) {
				partyACharacters = partyA.iterator();
				partyBCharacters = partyB.iterator();
				partyANext = partyACharacters.next();
				partyBNext = partyBCharacters.next();
			} else {
				if (partyBNext == null || (partyANext != null && partyANext.getSpeed() > partyBNext.getSpeed())) {
					partyANext.selectAction(partyA, partyB, this);
					if (partyACharacters.hasNext())
						partyANext = partyACharacters.next();
					else
						partyANext = null;
				} else if (partyANext == null || (partyBNext != null && partyANext.getSpeed() < partyBNext.getSpeed())) {
					partyBNext.selectAction(partyB, partyA, this);
					if (partyBCharacters.hasNext())
						partyBNext = partyBCharacters.next();
					else
						partyBNext = null;
				} else {
					int luckDifference = partyANext.getLuck() - partyBNext.getLuck();
					int luckCenter = 50 + luckDifference;
					
					if (Math.random() < (luckCenter / 100)) {
						partyANext.selectAction(partyA, partyB, this);
						if (partyACharacters.hasNext())
							partyANext = partyACharacters.next();
						else
							partyANext = null;						
					} else {
						partyBNext.selectAction(partyB, partyA, this);
						if (partyBCharacters.hasNext())
							partyBNext = partyBCharacters.next();
						else
							partyBNext = null;		
					}
				}
			}
		}
		this.end();
	}
	
	public void end() {
		this.ended = true;
	}
	
	public Item[] lootSpoils() {
		if (this.ended == false)
			throw new IllegalStateException("Cannot loot while the engagement is ongoing");
		
		Item[] spoils = new Item[partyB.size()];
		
		int i = 0;
		for (Antagonist foe : partyB) {
			spoils[i++] = foe.loot();
		}
		
		return spoils;
	}
	
	public boolean tryFlee() {
		if (!escapable) {
			this.game.report("You cannot escape!");
			return false;
		}
		// Todo: Add flee mechanics
		return true;
	}
}
