package events;

import behaviors.Behavior;
import behaviors.Gun;
import behaviors.MoveHorizontally;
import behaviors.MoveVertically;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;

import java.util.HashMap;

public class SpriteKeyPress {

	private MapProperty<KeyCode, Behavior> userPressBehaviors;

	public SpriteKeyPress() {
		userPressBehaviors = new SimpleMapProperty<KeyCode, Behavior>(FXCollections.observableMap(new HashMap<KeyCode, Behavior>()));
	}
	
	public SpriteKeyPress(MapProperty<KeyCode, Behavior> userPressBehaviors) {
		this.userPressBehaviors = userPressBehaviors;
	}
	
	public MapProperty<KeyCode, Behavior> getUserPressBehaviors() {
		return userPressBehaviors;
	}
	
	public void addUpwardsMovement(KeyCode key) {
		Behavior defaultUpPressMovement = new MoveVertically(-5);
		userPressBehaviors.put(key, defaultUpPressMovement);
	}
	
	public void addDownwardsMovement(KeyCode key) {
		Behavior defaultDownPressMovement = new MoveVertically(5);
		userPressBehaviors.put(key, defaultDownPressMovement);
	}
	
	public void addLeftwardsMovement(KeyCode key) {
		Behavior defaultLeftPressMovement = new MoveHorizontally(-5);
		userPressBehaviors.put(key, defaultLeftPressMovement);
	}
	
	public void addRightwardsMovement(KeyCode key) {
		Behavior defaultRightPressMovement = new MoveHorizontally(5);
		userPressBehaviors.put(key, defaultRightPressMovement);
	}
	
	public void addShooting(KeyCode key) {
		userPressBehaviors.put(key, new Gun());
	}

}
