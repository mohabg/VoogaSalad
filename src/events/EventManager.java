package events;

import java.util.ArrayList;
import java.util.List;

import collisions.Collision;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import level.LevelProperties;

public class EventManager implements ICollisionHandler, IInputHandler {
	
	private List<ICollisionHandler> myCollisionHandlers;
	private List<IInputHandler> myInputHandlers;

	public EventManager() {
		myCollisionHandlers = new ArrayList<>();
		myInputHandlers = new ArrayList<>();
	}
	
	public void addCollisionHandler(ICollisionHandler handler) {
		myCollisionHandlers.add(handler);
	}
	
	public void addInputHandler(IInputHandler handler) {
		myInputHandlers.add(handler);
	}

	@Override
	public void applyCollision(Collision one, Collision two, LevelProperties levelProperties) {
		for ( ICollisionHandler handler: myCollisionHandlers)
			handler.applyCollision(one, two, levelProperties);
	}

	@Override
	public void mouseClickEvent(MouseEvent event) {
		for ( IInputHandler handler: myInputHandlers)
			handler.mouseClickEvent(event);
	}

	@Override
	public void keyPressEvent(KeyEvent event) {
		for ( IInputHandler handler: myInputHandlers)
			handler.keyPressEvent(event);
	}

	@Override
	public void keyReleaseEvent(KeyEvent event) {
		for ( IInputHandler handler: myInputHandlers)
			handler.keyReleaseEvent(event);
	}

}
