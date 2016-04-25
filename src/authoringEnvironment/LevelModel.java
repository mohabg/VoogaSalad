package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob
 */
import gameElements.Sprite;
import goals.Goal;
import goals.Goal.Goals;
import goals.GoalProperties;
import interfaces.ITab;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;

import java.util.*;
import java.util.stream.Collectors;

import authoringEnvironment.mainWindow.GameAuthoringTab;
import behaviors.Behavior;
import collisions.Collision;

public class LevelModel {
	private ListProperty<Goal> myGoals;
//	private MapProperty<KeyCode, KeyboardActions> myKeyMap;
	private IntegerProperty numGoals;
	private List<Sprite> myList;
	private String myBackground;

	public LevelModel() {


		myBackground = "";
		myGoals = new SimpleListProperty<Goal>(FXCollections.observableList(new ArrayList<Goal>()));

		ObservableMap<KeyCode, KeyboardActions> om1 = FXCollections
				.observableMap(new HashMap<KeyCode, KeyboardActions>());
//		myKeyMap = new SimpleMapProperty<KeyCode, KeyboardActions>(om1);
		numGoals = new SimpleIntegerProperty();
		myList = new ArrayList<Sprite>();
	}

	public LevelModel(ITab levelTab) {
		this();
		myBackground = levelTab.getBackground();
		
		myList.addAll(((GameAuthoringTab) levelTab).getList());
	}

	public LevelModel(Level l) {
		this();
		LevelProperties myProperties = l.getLevelProperties();
//		List<GoalProperties> goalproperties = l.getGoalList().stream().map(goal->goal.getGoalProperties()).collect(Collectors.toList());
//		myGoals.addAll(myProperties.getGoalProperties().stream().map(gp-> gp.getMyGoal()).collect(Collectors.toList()));

	}


//	public Map<KeyCode, KeyboardActions> getMyKeyMap() {
//		return myKeyMap;
//	}

	public List<Goal> getMyGoals() {
		return myGoals;
	}
	
	public List<Sprite> getMySpriteList(){
		return myList;
	}
	
	public Integer getNumGoals(){
		return numGoals.get();
	}
	
	public String getBackground(){
		return myBackground;
	}

}
