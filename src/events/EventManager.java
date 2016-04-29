package events;

import java.util.ArrayList;
import java.util.List;

import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import gameElements.SpriteMap;
import javafx.beans.property.MapProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import level.LevelProperties;

/**
 * 
 * @author gauravkumar
 *
 */
public class EventManager {
	
	private List<Event> myEvents;
	//private IInputHandler myInputHandler;
	
	public EventManager() {
		myEvents = new ArrayList<Event>();
		//myInputHandler = new InputHandler();
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
		myEvents = events;
	}
	
	/*public void setInputHandler(IInputHandler handler) {
		myInputHandler = handler; 
	}*/
}
