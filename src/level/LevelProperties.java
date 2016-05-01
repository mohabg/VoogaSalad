package level;

import collisions.Collision;
import gameElements.ISprite;
import gameElements.Score;
import gameElements.Sprite;
import gameElements.SpriteMap;
import gameElements.Time;
import goals.Goals;
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

public class LevelProperties implements ILevelProperties {
	
	private IntegerProperty levelID;
	private StringProperty levelName;
	private IntegerProperty nextLevel;
	private IntegerProperty previousLevel;
	private IntegerProperty numGoals;
	private Score score;
	private SpriteMap spriteMap;
	private List<Goal> goalList;
	private Time time;
	private Map<KeyCode, KeyboardActions> keyMapping;
	private Map<String, List<Sprite>> spriteTypes;
	private List<GoalProperties> goalProperties;
	private ISprite[] collidingSprites;
	private IntegerProperty goalCount;
	private BooleanProperty isFinished;
//  private BooleanProperty shouldRestart;
	
	public LevelProperties() {
		score = new Score();
		time = new Time();
		goalCount = new SimpleIntegerProperty(0);
		isFinished = new SimpleBooleanProperty(false);
//		shouldRestart = new SimpleBooleanProperty(false);
		spriteMap = new SpriteMap();
		levelID = new SimpleIntegerProperty(0);
		levelName = new SimpleStringProperty("");
		nextLevel = new SimpleIntegerProperty(1);
		previousLevel = new SimpleIntegerProperty(-1);
		numGoals = new SimpleIntegerProperty(1);
		goalList = new ArrayList<>();
		goalProperties = new ArrayList<>();
	//	goalProperties.add(new GoalProperties(Goals.StayAliveGoal));
		HashMap<KeyCode, KeyboardActions> myBehaviorsMap = new HashMap<KeyCode, KeyboardActions>();
		ObservableMap<KeyCode, KeyboardActions> om1 = FXCollections.observableMap(myBehaviorsMap);
		keyMapping = new SimpleMapProperty<KeyCode, KeyboardActions>(om1);
		ObservableMap<String, List<Sprite>> om2 = FXCollections.observableMap(new HashMap<String, List<Sprite>>());
		spriteTypes = new SimpleMapProperty<String, List<Sprite>>(om2);
		collidingSprites = new ISprite[2];
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
	
	public void addGoal(GoalProperties goal){
		this.getGoalProperties().add(goal);
	}

	public void setCollidingSprites(ISprite spriteArr, ISprite spriteArr2) {
		collidingSprites[0] = spriteArr;
		collidingSprites[1] = spriteArr2;
	}

	public ISprite[] getCollidingSprites() {
		return collidingSprites;
	}

	public ISprite getSpriteForCollision(Collision collision) {
		for (ISprite sprite : collidingSprites) {
			for(Collision col : sprite.getCollisions()){
				if(col.getClass().getName().equals(collision.getClass().getName())){
					return sprite;
				}
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

	public Integer getNextLevelID() {
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

	public SpriteMap getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(SpriteMap spriteMap) {
		this.spriteMap = spriteMap;
	}

	public Map<String, List<Sprite>> getSpriteTypes() {
		return spriteTypes;
	}

	public void addSpriteType(Sprite sprite) {
		if ( spriteTypes.get(sprite.getMyRef()) ==  null ) {
			List<Sprite> newList = new ArrayList<Sprite>();
			newList.add(sprite);
			spriteTypes.put(sprite.getMyRef(),newList);
		} else
			spriteTypes.get(sprite.getMyRef()).add(sprite);
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

	public DoubleProperty getCurrentPoints() {
		// return score.getScoreValue().get();
		return getScore();
	}

	public void setCurrentPoints(Integer currentPoints) {
		this.score.setScoreValue(currentPoints);
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
	public DoubleProperty getScore() {
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
	public ISprite getUserControlledSprite(){
		return this.getSpriteMap().getUserControlledSprite();
	}

	public double getUserX() {
		return this.getSpriteMap().getUserControlledSprite().getSpriteProperties().getX();
	}

	public double getUserY() {
		return this.getSpriteMap().getUserControlledSprite().getSpriteProperties().getY();
	}
	public double distanceFromUser(Sprite sprite){
		double xDiff = Math.abs(sprite.getSpriteProperties().getX() - getUserX());
		double yDiff = Math.abs(sprite.getSpriteProperties().getY() - getUserY());
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}
	public double angleToUser(Sprite sprite){
		//Probably does not work as expected
		double angleToFace = Math.atan(getUserY() - sprite.getSpriteProperties().getY() / getUserX() - sprite.getSpriteProperties().getX());
        return angleToFace - sprite.getSpriteProperties().getAngle();
	}
/*
	public BooleanProperty getShouldRestart() {
		return shouldRestart;
	}

	public void setShouldRestart(BooleanProperty shouldRestart) {
		this.shouldRestart = shouldRestart;
	}
	
	public void setShouldRestart (Boolean bool){
		setShouldRestart(new SimpleBooleanProperty(bool));
	}
	*/
}
