package exampledata;

public class Level {
	Character character;
	Goal myGoal;

	public Level(int charhealth, boolean goal) {
		character = new Character(charhealth);
		myGoal = new Goal(goal);
		// TODO Auto-generated constructor stub
	}

}
