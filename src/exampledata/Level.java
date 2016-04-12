package exampledata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gameElements.Time;

public class Level {
	Character character;
	Goal myGoal;
	ObservableList<Integer> testList;
	Time myTime;
	
	public Level(int charhealth, boolean goal) {
		character = new Character(charhealth);
		myGoal = new Goal(goal);
		testList = FXCollections.observableArrayList();
		testList.add(new Integer(4));
		// TODO Auto-generated constructor stub
	}

}
