package events;

import java.util.ArrayList;
import java.util.List;

import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import gameElements.SpriteMap;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import level.LevelProperties;

/**
 * Contains all of the events that occur in the game
 * 
 * @author gauravkumar
 *
 */

public class EventManager {
	
	private ListProperty<Event> myEvents;
	//private IInputHandler myInputHandler;
	
	public EventManager() {
		myEvents = new SimpleListProperty(FXCollections.<Event>observableList(new ArrayList<Event>()));
	}
	
	public void doEvents(IActions action, LevelProperties levProps) {
		for ( Event e: myEvents) {
			e.doEvent(action, levProps);
		}
	}
	
/*	@Override
	public void mouseClickEvent(MouseEvent event) {
		myInputHandler.mouseClickEvent(event);
	}*/

	public void keyPress(KeyCode code, IActions action, LevelProperties levProps) {
		for ( Event e: myEvents) {
			if ( e instanceof KeyPressEvent ) {
				KeyPressTrigger trigger = (KeyPressTrigger) e.getTrigger();
				if (trigger.getCode().equals(code)) {
					trigger.setIsTriggered(true);
				}
			}
		}
	}

	
	public void keyRelease(KeyCode code, IActions action, LevelProperties levProps){
		for ( Event e: myEvents) {
			if ( e instanceof KeyPressEvent ) {
				KeyPressTrigger trigger = (KeyPressTrigger) e.getTrigger();
				if (trigger.getCode().equals(code)) {
					trigger.setIsTriggered(false);
				}
			}
		}
	}

	
	/*public void setSpriteActions(MapProperty<KeyCode, Executable> userPressActions) {
		myInputHandler.setSpriteActions(userPressActions);
	}*/
	
	public void addEvent(Event event) {
		myEvents.add(event);
	}

	public void setEvents(List<Event> events) {
		myEvents.get().clear();
		myEvents.get().addAll(events);
	}
	
	/*public void setInputHandler(IInputHandler handler) {
		myInputHandler = handler; 
	}*/
}
