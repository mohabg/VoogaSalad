package events;

import javafx.scene.input.KeyEvent;

public class KeyTrigger implements Trigger {
	
	private boolean isTriggered;
	private KeyEvent event;

	public KeyTrigger(KeyEvent event) {
		isTriggered = false;
		this.event = event;
	}

	@Override
	public boolean isTriggered() {
		return isTriggered;
	}

}
