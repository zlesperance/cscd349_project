import java.util.Iterator;

public class Engagement {
	private Party partyA, partyB;
	private boolean ended;
	
	public Engagement(Party partyA, Party partyB) {
		this.partyA = partyA;
		this.partyB = partyB;
	}
	
	public void begin() {
		this.ended = false;
		Iterator<Character> partyACharacters = partyA.iterator(),
				partyBCharacters = partyB.iterator();
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
				if (partyBNext == null || partyANext.getSpeed() > partyBNext.getSpeed()) {
					partyANext.selectAction(partyA, partyB, this);
					if (partyACharacters.hasNext())
						partyANext = partyACharacters.next();
					else
						partyANext = null;
				} else if (partyANext == null || partyANext.getSpeed() < partyBNext.getSpeed()) {
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
}
