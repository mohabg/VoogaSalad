package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob
 */
import gameElements.Sprite;
import goals.Goal;
import goals.GoalProperties;
import interfaces.ITab;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import level.Level;
import level.LevelProperties;

import java.util.*;
import java.util.stream.Collectors;

import authoringEnvironment.mainWindow.GameAuthoringTab;

public class LevelModel {

	private LevelProperties myProperties;
	private ListProperty<Goal> myGoals;
	private List<Sprite> myList;
	private String myBackground;

	public LevelModel() {
		myList = new ArrayList<Sprite>();
		myBackground = "";
//		myList =  ;
		myProperties = new LevelProperties();
		myGoals = new SimpleListProperty<Goal>(FXCollections.observableList(new ArrayList<Goal>()));

	}

	public LevelModel(ITab levelTab) {
		this();
		myBackground = levelTab.getBackground();
		
		myList.addAll(((GameAuthoringTab) levelTab).getList());
	}

	public LevelModel(Level l) {
		this();
		myList.addAll(l.getSpriteMap().getSpriteMap().values());
		myProperties = l.getLevelProperties();
//		List<GoalProperties> goalproperties = l.getGoalList().stream().map(goal->goal.getGoalProperties()).collect(Collectors.toList());
		myGoals.addAll(l.getGoalList());

	}

	public List<Sprite> getMySpriteList() {
		return myList;
	}

	public LevelProperties getMyProperties() {
		return myProperties;
	}

	public List<Goal> getMyGoals() {
		return myGoals;
	}

}
