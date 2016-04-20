package level;

import java.util.HashMap;
import java.util.Map;

import collisions.Collision;
import gameElements.Sprite;
import gameElements.Time;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;

/**
 * Holds all of the information a level has, which is then applied as a parameter to the constructor for Level. 
 */


public class LevelProperties {
	private Integer levelID;
	private String levelName;
	private Integer nextLevel;
	private Integer previousLevel;
	private Integer currentPoints;
	private Time time;
	private Integer numGoals;
	private HashMap<KeyCode, KeyboardActions> keyMapping;
	private Sprite[] collidingSprites;
	
	public LevelProperties(){
		setLevelID(null);
		setLevelName("");
		setNextLevel(-1);
		setPreviousLevel(-1);
		setNumGoals(1);
		keyMapping=new HashMap<KeyCode, KeyboardActions>();
		keyMapping.put(KeyCode.DOWN, KeyboardActions.MoveDown);
		collidingSprites = new Sprite[2];
	}
	public LevelProperties(Integer levelID, String levelName, Integer nextLevel, Integer previousLevel, Integer numberOfGoals) {
		setLevelID(levelID);
		setLevelName(levelName);
		setNextLevel(nextLevel);
		setPreviousLevel(previousLevel);
		keyMapping=new HashMap<KeyCode, KeyboardActions>();
		numGoals=numberOfGoals;
		collidingSprites = new Sprite[2];
	}
	public void setCollidingSprites(Sprite one, Sprite two){
		collidingSprites[0] = one;
		collidingSprites[1] = two;
	}
	public Sprite[] getCollidingSprites(){
		return collidingSprites;
	}
	public Sprite getSpriteForCollision(Collision collision){
		for(Sprite sprite : collidingSprites){
			if(sprite.getCollisions().contains(collision)){
				return sprite;
			}
		}
		return null;
	}
	public Integer getLevelID() {
		return levelID;
	}
	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Integer getNextLevel() {
		return nextLevel;
	}
	public void setNextLevel(Integer nextLevel) {
		this.nextLevel = nextLevel;
	}
	public Integer getPreviousLevel() {
		return previousLevel;
	}
	public void setPreviousLevel(Integer previousLevel) {
		this.previousLevel = previousLevel;
	}
	public Integer getCurrentPoints() {
		return currentPoints;
	}
	public void setCurrentPoints(Integer currentPoints) {
		this.currentPoints = currentPoints;
	}
	public Integer getNumGoals() {
		return numGoals;
	}
	public void setNumGoals(Integer numGoals) {
		this.numGoals = numGoals;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public KeyboardActions getKeyboardAction(KeyCode key){
		if(!keyMapping.keySet().contains(key)) return KeyboardActions.Default;
		return keyMapping.get(key);
	}

	
}
