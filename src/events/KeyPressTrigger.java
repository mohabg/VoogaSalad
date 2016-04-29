package events;

import javafx.scene.input.KeyEvent;
public class KeyPressTrigger implements Trigger {
	
	private boolean isTriggered;
	private KeyEvent event;

	public KeyPressTrigger(KeyEvent event) {
		isTriggered = false;
		this.event = event;
	}
	
	public void checkKeyPressed() {
	}

	@Override
	public boolean isTriggered() {
		return isTriggered;
	}

}
