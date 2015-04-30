package cscd349_project;

public class EngagementTester {
	public static void main (String[] args) {
		testFightVsSlime();
		
		Game.end();
	}
	
	public static void testFightVsSlime() {
		Party protagonists = new Party(new Character[]{new Fighter("Tim")});
		Party antagonists = new Party(new Character[]{new Slime(), new Slime()});
		Engagement fight = new Engagement(protagonists, antagonists);
		fight.begin();
	}
}
