package level;

import gameElements.Time;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;

import java.util.HashMap;

/**
 * Holds all of the information a level has, which is then applied as a parameter to the constructor for Level. 
 */


public class LevelProperties {
	private IntegerProperty levelID;
	private StringProperty levelName;
	private IntegerProperty nextLevel;
	private IntegerProperty previousLevel;
	private IntegerProperty currentPoints;
	private Time time;
	private IntegerProperty numGoals;
	private MapProperty<KeyCode, KeyboardActions> keyMapping;
	
	public LevelProperties(){
        levelID = new SimpleIntegerProperty();
        levelName = new SimpleStringProperty();
        nextLevel = new SimpleIntegerProperty();
        previousLevel = new SimpleIntegerProperty();
        currentPoints = new SimpleIntegerProperty();
        numGoals = new SimpleIntegerProperty();
        HashMap<KeyCode,KeyboardActions> myBehaviorsMap = new HashMap<KeyCode, KeyboardActions>();
        myBehaviorsMap.put(KeyCode.DOWN, KeyboardActions.MoveDown);
        ObservableMap<KeyCode,KeyboardActions> om1 = FXCollections.observableMap(myBehaviorsMap);
        keyMapping=new SimpleMapProperty<KeyCode, KeyboardActions>(om1);
        setLevelID(0);
        setLevelName("");
        setNextLevel(-1);
        setPreviousLevel(-1);
        setCurrentPoints(0);
        setNumGoals(1);


    }
	public LevelProperties(Integer levelID, String levelName, Integer nextLevel, Integer previousLevel, Integer numberOfGoals) {
        this();
		setLevelID(levelID);
		setLevelName(levelName);
		setNextLevel(nextLevel);
		setPreviousLevel(previousLevel);
        setNumGoals(1);
        //keyMapping=new HashMap<KeyCode, KeyboardActions>();
		numGoals.set(numberOfGoals);
	}
	public Integer getLevelID() {
		return levelID.intValue();
	}
	public void setLevelID(Integer levelID) {
		this.levelID.set(0);
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
		return currentPoints.intValue();
	}
	public void setCurrentPoints(Integer currentPoints) {
		this.currentPoints.set(currentPoints);
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
	
	public KeyboardActions getKeyboardAction(KeyCode key){
		if(!keyMapping.keySet().contains(key)) return KeyboardActions.Default;
		return keyMapping.get(key);
	}

	
}
