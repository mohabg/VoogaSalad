package gameElements;

import exampledata.Level;

import java.util.List;
import java.util.ArrayList;

public class Game {
	
	private List<Level> myGameLevels;
	private int currLevelNum;

	public Game() {
		myGameLevels = new ArrayList<Level>();
		currLevelNum = 0;
	}
	
	public Game(int initialLevel) {
		myGameLevels = new ArrayList<Level>();
		currLevelNum = initialLevel;
	}
	
	public void step(double elapsedTime) {
		//TODO: implement
		//Step through current level
		// if level is complete,
		// advance to next level
	}
	
	public void nextLevel(int nextLevelNum) {
		currLevelNum = nextLevelNum;
	}
	
	public void parseXML() {
		//TODO: implement
	}
	
	public void startGame() {
		//TODO: implement
	}
	
	public void endGame() {
		//TODO: implement
	}
	
	public void pauseGame() {
		//TODO: implement
	}

}
