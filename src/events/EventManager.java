package events;

import java.util.ArrayList;
import java.util.List;

import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionHandler;
import gameElements.SpriteMap;
import javafx.beans.property.MapProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import level.LevelProperties;

public class EventManager implements ICollisionHandler, IInputHandler {
	
	private ICollisionHandler myCollisionHandler;
	private IInputHandler myInputHandler;
	
	public EventManager() {
		myCollisionHandler = new CollisionHandler();
		myInputHandler = new InputHandler();
	}

	public EventManager(ICollisionHandler myCollisionHandler, IInputHandler myInputHandler) {
		this.myCollisionHandler = myCollisionHandler;
		this.myInputHandler = myInputHandler;
	}

	public void setCollisionHandler(ICollisionHandler handler) {
		myCollisionHandler = handler;
	}
	
	public void setInputHandler(IInputHandler handler) {
		myInputHandler = handler; 
	}

	@Override
	public void applyCollision(Collision one, Collision two, LevelProperties levelProperties) {
		myCollisionHandler.applyCollision(one, two, levelProperties);
	}

	@Override
	public void mouseClickEvent(MouseEvent event) {
		myInputHandler.mouseClickEvent(event);
	}

	@Override
	public void keyPress(KeyEvent event, IActions action) {
		myInputHandler.keyPress(event, action);
	}
	@Override
	public void keyRelease(KeyEvent event, IActions action){
		myInputHandler.keyRelease(event, action);
	}
	
	@Override
	public void checkCollisions(LevelProperties levelProperties) {
		myCollisionHandler.checkCollisions(levelProperties);
	}

	@Override
	public void setSpriteActions(MapProperty<KeyCode, Executable> userPressActions) {
		myInputHandler.setSpriteActions(userPressActions);
	}

}
