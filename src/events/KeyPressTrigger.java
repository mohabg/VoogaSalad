package events;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.scene.input.KeyEvent;
public class KeyPressTrigger implements Trigger {
	
	private boolean isTriggered;
	@IgnoreField
	private KeyEvent event;

	public KeyPressTrigger(){
		isTriggered = false;
	}
	
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
