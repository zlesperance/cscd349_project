import java.util.Comparator;

public class SpeedComparator implements Comparator<Character> {

	@Override
	public int compare(Character characterA, Character characterB) {
		return characterA.getSpeed() - characterB.getSpeed();
	}

}
