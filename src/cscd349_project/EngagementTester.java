package cscd349_project;

public class EngagementTester {
	public static void main (String[] args) {
		testEngagementFighterVsSlime();
		
		Game.end();
	}
	
	public static void testEngagementFighterVsSlime() {
		Party protagonists = new Party(new Character[]{new Fighter("Tim")});
		Party antagonists = new Party(new Character[]{new Slime("Slime A"), new Slime("Slime B")});
		Engagement fight = new Engagement(protagonists, antagonists);
		fight.begin();
	}
}
