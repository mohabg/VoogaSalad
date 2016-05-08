package game;

import gameElements.ViewPoint;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Alert;
import level.Level;
import level.LevelProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Can get/set information about the game in this class, including what the
 * current level is and game info. One can create and delete levels and restart
 * the game.
 * 
 * @author gauravkumar
 */

public class Game {

	private GameInfo myInfo;
	private List<Level> myGameLevels;
	private int currLevelNum;
	private ViewPoint viewPoint;
	private BooleanProperty isFinished;

	public Game(GameInfo gameInfo) {
		this();
		this.setGameInfo(gameInfo);

	}

	
	public Game() {
		myGameLevels = new ArrayList<Level>();
		currLevelNum = 0;
		myInfo = new GameInfo();
		isFinished = new SimpleBooleanProperty(false);

	}

	public Game(GameInfo gameInfo, DoubleProperty width, DoubleProperty height) {
		this(gameInfo);
		viewPoint = new ViewPoint(width, height);
	}

	public Game(DoubleProperty width, DoubleProperty height) {
		this();
		viewPoint = new ViewPoint(width, height);
	}

	public GameInfo getGameInfo() {
		return myInfo;
	}

	public void setGameInfo(GameInfo newInfo) {
		myInfo = newInfo;
	}

	public int getNumLevels() {
		return myGameLevels.size();
	}

	public void nextLevel(int nextLevelNum) {
		currLevelNum = nextLevelNum;
	}

	public void setCurrentLevel(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if (myGameLevels.get(index) == null) {
			myGameLevels.add(index, new Level());
		}
		currLevelNum = index;
	}

	public Level getCurrentLevel() {
		if(myGameLevels.get(currLevelNum).equals(null)){
			System.out.println("hi");
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Game Information");
        	alert.setHeaderText("Game Result");
        	alert.setContentText("Congrats you've won the game bitch");
        	alert.showAndWait();
        	return null;
		}else{
			return myGameLevels.get(currLevelNum);
		}

	}

	public void createLevel(int levelID, Level level) {
		myGameLevels.add(levelID, level);
	}

	/**
	 * @param levelID
	 *            The ID that you want your level to have
	 * @param levelProperties
	 *            the method uses the properties defined here to create a new
	 *            level
	 */

	public void createLevel(int levelID, LevelProperties levelProperties) {
		Level newLevel = new Level();
		// System.out.println("bug" + newLevel.getGoalList().size());
		levelProperties.setLevelID(levelID);
		newLevel.setLevelProperties(levelProperties);
		myGameLevels.add(levelID, newLevel);
		// viewPoint.updateViewPoint(newLevel);

	}

	/**
	 * returns the original level of the game
	 */

	public void addLevel(int levelID, Level level) {
		myGameLevels.add(level);
	}

	public Level restartGame() {
		return myGameLevels.get(0);
	}

	public ViewPoint getViewPoint() {
		return viewPoint;
	}
 
	public void setViewPoint(ViewPoint viewPoint) {
		this.viewPoint = viewPoint;
	}

	public BooleanProperty getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished.set(isFinished);
	}


	public boolean hasNextLevel() {
		return this.myGameLevels.size() > this.getCurrentLevel().getLevelProperties().getNextLevelID();
	}
}
