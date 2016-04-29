package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob
 */
import authoringEnvironment.mainWindow.GameAuthoringTab;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import events.Event;
import gameElements.Sprite;
import goals.Goal;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LevelModel {
	private ListProperty<Goal> myGoals;
	private ListProperty<Event> myEvents;
	private IntegerProperty numGoals;
	@IgnoreField
	private List<Sprite> myList;
	@IgnoreField
	private String myBackground;

	public LevelModel() {
		myBackground = "";
		myGoals = new SimpleListProperty<Goal>(FXCollections.observableList(new ArrayList<Goal>()));

		ObservableMap<KeyCode, KeyboardActions> om1 = FXCollections
				.observableMap(new HashMap<KeyCode, KeyboardActions>());
		
		myEvents = new SimpleListProperty<Event>(FXCollections.<Event>observableList(new ArrayList<Event>()));
		myEvents.sizeProperty().addListener((o, ov, nv) -> {
			System.out.println("i changed");
		});
//		myKeyMap = new SimpleMapProperty<KeyCode, KeyboardActions>(om1);
		numGoals = new SimpleIntegerProperty(1);
		myList = new ArrayList<Sprite>();
	}

	public LevelModel(GameAuthoringTab levelTab) {
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
	public List<Event> getMyEvents(){
		return myEvents;
	}

	public void setBackground(String background) {
		myBackground = background;
		
	}

	public void addSprites(List<Sprite> list) {
		myList.addAll(list);		
	}

}
