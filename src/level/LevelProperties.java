package level;

import java.util.HashMap;
import java.util.Map;

import gameElements.Score;
import collisions.Collision;
import gameElements.Sprite;
import gameElements.Time;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;

import java.util.HashMap;

/**
 * Holds all of the information a level has, which is then applied as a
 * parameter to the constructor for Level.
 */

public class LevelProperties {

	private IntegerProperty levelID;
	private StringProperty levelName;
	private IntegerProperty nextLevel;
	private IntegerProperty previousLevel;
//	private Score score;
	private IntegerProperty score;
	private Time time;
	private IntegerProperty numGoals;
	private Map<KeyCode, KeyboardActions> keyMapping;
	private Sprite[] collidingSprites;

	public LevelProperties() {
//		score = new Score();
		score = new SimpleIntegerProperty();
		levelID = new SimpleIntegerProperty();
		levelName = new SimpleStringProperty();
		nextLevel = new SimpleIntegerProperty();
		previousLevel = new SimpleIntegerProperty();
		numGoals = new SimpleIntegerProperty();
		HashMap<KeyCode, KeyboardActions> myBehaviorsMap = new HashMap<KeyCode, KeyboardActions>();
		myBehaviorsMap.put(KeyCode.DOWN, KeyboardActions.MoveDown);
		ObservableMap<KeyCode, KeyboardActions> om1 = FXCollections.observableMap(myBehaviorsMap);
		keyMapping = new SimpleMapProperty<KeyCode, KeyboardActions>(om1);
		setLevelID(0);
		setLevelName("");
		setNextLevel(-1);
		setPreviousLevel(-1);
		setCurrentPoints(0);
		setNumGoals(1);
		collidingSprites = new Sprite[2];
	}

	public LevelProperties(Integer levelID, String levelName, Integer nextLevel, Integer previousLevel,
			Integer numberOfGoals) {
		this();
		setLevelID(levelID);
		setLevelName(levelName);
		setNextLevel(nextLevel);
		setPreviousLevel(previousLevel);
		keyMapping = new HashMap<KeyCode, KeyboardActions>();
		numGoals.set(numberOfGoals);
		collidingSprites = new Sprite[2];
	}

	private void defaultLevelPropertyInit() {
		collidingSprites = new Sprite[2];
	}

	public void setCollidingSprites(Sprite one, Sprite two) {
		collidingSprites[0] = one;
		collidingSprites[1] = two;
	}

	public Sprite[] getCollidingSprites() {
		return collidingSprites;
	}

	public Sprite getSpriteForCollision(Collision collision) {
		for (Sprite sprite : collidingSprites) {
			if (sprite.getCollisions().contains(collision)) {
				return sprite;
			}
		}
		return null;
	}

	public Integer getLevelID() {
		return levelID.intValue();
	}

	public void setLevelID(Integer levelID) {
		this.levelID.set(levelID);
	}

	public String getLevelName() {
		return levelName.getValue();
	}

	public void setLevelName(String levelName) {
		this.levelName.set(levelName);
	}

	public Integer getNextLevel() {
		return nextLevel.intValue();
	}

	public void setNextLevel(Integer nextLevel) {
		this.nextLevel.set(nextLevel);
	}

	public Integer getPreviousLevel() {
		return previousLevel.intValue();
	}

	public void setPreviousLevel(Integer previousLevel) {
		this.previousLevel.set(previousLevel);
	}

	public Integer getCurrentPoints() {
//		return score.getScoreValue().get();
		return score.get();
	}

	public void setCurrentPoints(Integer currentPoints) {
		this.score.equals(currentPoints);
	}

	public Integer getNumGoals() {
		return numGoals.intValue();
	}

	public void setNumGoals(Integer numGoals) {
		this.numGoals.set(numGoals);
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public KeyboardActions getKeyboardAction(KeyCode key) {
		if (!keyMapping.keySet().contains(key))
			return KeyboardActions.Default;
		return keyMapping.get(key);
	}

//	public Score getScore() {
//		return score;
//	}
	public IntegerProperty getScore(){
		return score;
	}
	public void addScore(int val){
		score.set(score.get()+val);
	}

//	public void setScore(Score score) {
//		this.score = score;
//	}

}
