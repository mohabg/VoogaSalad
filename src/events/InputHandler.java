package events;

import java.util.HashMap;
import java.util.Map;

import behaviors.Behavior;
import behaviors.IActions;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import level.LevelProperties;

public class InputHandler {

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
	
	public void addGameAction(KeyCode code, Executable action) {
		gameActions.put(code, action);
	}
	
	public void addSpriteAction(KeyCode code, Executable action) {
		spriteActions.put(code, action);
	}
	
	public void setSpriteActions(MapProperty<KeyCode, Executable> userPressActions) {
		spriteActions = userPressActions;
	}

	@Override
	public void mouseClickEvent(MouseEvent event) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPress(KeyEvent event, IActions action, LevelProperties levProps) {
		if ( gameActions.containsKey(event.getCode())){
			gameActions.get(event.getCode()).execute(action, levProps);	
		}
		else if ( spriteActions.containsKey(event.getCode())){
			spriteActions.get(event.getCode()).execute(action, levProps);
		}
		//else throw some error
	}
	@Override
	public void keyRelease(KeyEvent event, IActions action, LevelProperties levProps){
		if ( gameActions.containsKey(event.getCode())){
			gameActions.get(event.getCode()).stop(action, levProps);	
		}
		else if ( spriteActions.containsKey(event.getCode())){
			spriteActions.get(event.getCode()).stop(action, levProps);
		}
	}
}
