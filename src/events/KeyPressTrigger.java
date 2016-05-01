package events;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.scene.input.KeyCode;
/**
 * 
 * @author gauravkumar
 *
 */
public class KeyPressTrigger implements Trigger {
	@IgnoreField
	private boolean isTriggered;	
	
	private KeyCode code;
	
	
	public KeyPressTrigger(){
		this.isTriggered = false; 
		this.code = KeyCode.A;
	}
	
	public KeyPressTrigger(KeyCode Code) {
		isTriggered = false;
		this.code = Code;
	}
	
	public KeyCode getCode() {
		return code;
	}

	public void setCode(KeyCode Code) {
		this.code = Code;
	}

	public void setIsTriggered(boolean trigger) {
		isTriggered = trigger;
	}

	@Override
	public boolean isTriggered() {
		return isTriggered;
	}
}
