public class DeadCharacterTester implements CharacterTester {

	@Override
	public boolean test(Character subject) {
		return subject.isDead();
	}

}
