package events;

import java.util.HashMap;
import java.util.Map;

import behaviors.Behavior;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class InputHandler implements IInputHandler {

	private MapProperty<KeyCode, Executable> gameActions;
	private MapProperty<KeyCode, Executable> spriteActions;
	
	public InputHandler() {
		gameActions = new SimpleMapProperty<KeyCode, 
				Executable>(FXCollections.observableMap(new HashMap<KeyCode, Executable>()));
		spriteActions = new SimpleMapProperty<KeyCode, 
				Executable>(FXCollections.observableMap(new HashMap<KeyCode, Executable>()));
	}
	
	public InputHandler(MapProperty<KeyCode, Executable> gameActions, 
			MapProperty<KeyCode, Executable> spriteActions) {
		this.gameActions = gameActions;
		this.spriteActions = spriteActions;
	}

	@Override
	public void mouseClickEvent(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyEvent(KeyEvent event) {
		gameActions.get(event.getCode()).execute();	
	}

}
