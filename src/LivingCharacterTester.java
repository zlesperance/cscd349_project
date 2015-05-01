public class LivingCharacterTester implements CharacterTester {

	@Override
	public boolean test(Character subject) {
		return !subject.isDead();
	}

}
