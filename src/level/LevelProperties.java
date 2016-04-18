package level;

import java.util.HashMap;
import java.util.Map;

import gameElements.Time;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;

/**
 * Holds all of the information a level has, which is then applied as a parameter to the constructor for Level. 
 */


public class LevelProperties {
	private String levelName;
	private Level nextLevel;
	private Level previousLevel;
	private Integer currentPoints;
	private Time time;
	private Integer numGoals;
	private HashMap<KeyCode, KeyboardActions> keyMapping;
	
	public LevelProperties(){
		setLevelName("");
		keyMapping=new HashMap<KeyCode, KeyboardActions>();
		keyMapping.put(KeyCode.DOWN, KeyboardActions.MoveDown);
	}
	public LevelProperties(Integer levelID, String levelName, Level nextLevel, Level previousLevel) {
		setLevelName(levelName);
		setNextLevel(nextLevel);
		setPreviousLevel(previousLevel);
		keyMapping=new HashMap<KeyCode, KeyboardActions>();
	}
	
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Level getNextLevel() {
		return nextLevel;
	}
	public void setNextLevel(Level nextLevel) {
		this.nextLevel = nextLevel;
	}
	public Level getPreviousLevel() {
		return previousLevel;
	}
	public void setPreviousLevel(Level previousLevel) {
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
