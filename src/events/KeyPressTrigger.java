package events;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.scene.input.KeyEvent;

public class KeyPressTrigger implements Trigger {
	@IgnoreField
	private boolean isTriggered;
	@IgnoreField	
	private KeyEvent event;
	
	public KeyPressTrigger(){
		this.isTriggered = false;
	}
	
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
