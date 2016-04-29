package events;

import javafx.scene.input.KeyEvent;
public class KeyPressTrigger implements Trigger {
	
	private boolean isTriggered;
	private KeyEvent event;

	public KeyPressTrigger(KeyEvent event) {
		isTriggered = false;
		this.event = event;
	}
	
	public KeyEvent getEvent() {
		return event;
	}

	public void setEvent(KeyEvent event) {
		this.event = event;
	}

	public void setIsTriggered(boolean trigger) {
		isTriggered = trigger;
	}

	@Override
	public boolean isTriggered() {
		return isTriggered;
	}

}
