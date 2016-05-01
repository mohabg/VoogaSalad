package authoringEnvironment;

/**
 * @author David Yan, Joe Jacob
 * Class to hold the information found in each level, including keybindings for level events and other level properties
 * 
 */
import authoringEnvironment.mainWindow.GameAuthoringTab;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import events.Event;
import gameElements.Sprite;
import goals.Goal;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import resources.BackEndData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Physics.PhysicsEngine;

public class LevelModel {
	private ListProperty<Goal> myGoals;
	private ListProperty<Event> myEvents;
	private BooleanProperty enableGravity;
	@IgnoreField
	private IntegerProperty numGoals;
	@IgnoreField
	private List<Sprite> myList;
	@IgnoreField
	private StringProperty myBackground;

	public LevelModel() {
		myBackground = new SimpleStringProperty();
		enableGravity = new SimpleBooleanProperty();
		myGoals = new SimpleListProperty<Goal>(FXCollections.observableList(new ArrayList<Goal>()));

		myEvents = new SimpleListProperty<Event>(FXCollections.<Event>observableList(new ArrayList<Event>()));
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
	}

	public BooleanProperty getEnableGravity() {
		return enableGravity;
	}

	public void setEnableGravity(BooleanProperty enableGravity) {
		this.enableGravity = enableGravity;
	}

	public List<Goal> getMyGoals() {
		return myGoals;
	}
	
	public List<Sprite> getMySpriteList(){
		return myList;
	}
	
	public Integer getNumGoals(){
		return numGoals.get();
	}
	
	public StringProperty getBackground(){
		return myBackground;
	}
	
	public List<Event> getMyEvents(){
		return myEvents;
	}


	public void setBackground(String background) {
		myBackground.setValue(background);	
	}

	public void addSprites(List<Sprite> list) {
		myList.addAll(list);		
	}

}
