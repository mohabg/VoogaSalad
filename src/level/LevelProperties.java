package level;

import collisions.Collision;
import gameElements.Score;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameElements.Time;
import goals.Goal.Goals;
import goals.Goal;
import goals.GoalProperties;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all of the information a level has, which is then applied as a
 * parameter to the constructor for Level.
 */

public class LevelProperties {
	
	private IntegerProperty levelID;
	private StringProperty levelName;
	private IntegerProperty nextLevel;
	private IntegerProperty previousLevel;
	private IntegerProperty numGoals;
	private Score score;
	private List<Sprite> sprites;
	private SpriteMap spriteMap;
	private List<Goal> goalList;
	private Time time;
	private Map<KeyCode, KeyboardActions> keyMapping;
	private List<GoalProperties> goalProperties;
	private Sprite[] collidingSprites;
	private IntegerProperty goalCount;
	private BooleanProperty isFinished;
	
	public LevelProperties() {
		score = new Score();
		// score = new SimpleIntegerProperty();
		/* 
		setLevelID(0);
		setLevelName("");
		setNextLevel(1);
		setPreviousLevel(-1);
		setCurrentPoints(0);
		setNumGoals(1);
		*/
		goalCount = new SimpleIntegerProperty(0);
		isFinished = new SimpleBooleanProperty(false);
		spriteMap = new SpriteMap();
		levelID = new SimpleIntegerProperty(0);
		levelName = new SimpleStringProperty("");
		nextLevel = new SimpleIntegerProperty(1);
		previousLevel = new SimpleIntegerProperty(-1);
		numGoals = new SimpleIntegerProperty(1);
		goalList = new ArrayList<>();
		goalProperties = new ArrayList<>();
		goalProperties.add(new GoalProperties(Goals.StayAliveGoal));
		HashMap<KeyCode, KeyboardActions> myBehaviorsMap = new HashMap<KeyCode, KeyboardActions>();
	//	myBehaviorsMap.put(KeyCode.DOWN, KeyboardActions.MoveDown);
		ObservableMap<KeyCode, KeyboardActions> om1 = FXCollections.observableMap(myBehaviorsMap);
		keyMapping = new SimpleMapProperty<KeyCode, KeyboardActions>(om1);
		
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

	public Score getScoreObject() {
		return this.score;
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public void setSprites(List<Sprite> sprites) {
		this.sprites = sprites;
	}

	public SpriteMap getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(SpriteMap spriteMap) {
		this.spriteMap = spriteMap;
	}

	public List<Goal> getGoalList() {
		return goalList;
	}

	public void setGoalList(List<Goal> goalList) {
		this.goalList = goalList;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Integer getCurrentPoints() {
		// return score.getScoreValue().get();
		return getScore().getValue();
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
	
	public void setKeyboardAction(KeyCode code, KeyboardActions action){
		keyMapping.put(code, action);
	}

	public KeyboardActions getKeyboardAction(KeyCode key) {
		if (!keyMapping.keySet().contains(key))
			return KeyboardActions.Default;
		return keyMapping.get(key);
	}

	/*
	 * public IntegerProperty getScore() { return
	 * getScoreObject().getScoreValue(); }
	 *
	 * public void addScore(int val){ getScoreObject().addScore(val); }
	 */
	public List<GoalProperties> getGoalProperties() {
		return goalProperties;
	}

	public void setGoalProperties(List<GoalProperties> goalProperties) {
		this.goalProperties = goalProperties;
	}

	/*
	 * public void setScore(Score score) { this.score = score; }
	 */
	public IntegerProperty getScore() {
		return getScoreObject().getScoreValue();
	}

	public void addScore(int val) {
		getScoreObject().addScore(val);
	}

	public Map<KeyCode, KeyboardActions> getKeyMapping() {
		return keyMapping;
	}

	public void setKeyMapping(Map<KeyCode, KeyboardActions> keyMapping) {
		this.keyMapping = keyMapping;
	}
	public int getGoalCount() {
		return this.goalCount.get();
	}

	public void setGoalCount(int goalCount) {
		this.goalCount.set(goalCount);
	}

	public boolean isFinished() {
		return isFinished.get();
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished.set(isFinished);
	}

}
