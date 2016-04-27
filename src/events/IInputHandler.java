package events;

import behaviors.IActions;
import javafx.beans.property.MapProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import level.LevelProperties;

public interface IInputHandler {	

	public void mouseClickEvent(MouseEvent event);
	public void keyPress(KeyEvent event, IActions action, LevelProperties levProps);
	public void keyRelease(KeyEvent event, IActions action, LevelProperties levProps);
	public void setSpriteActions(MapProperty<KeyCode, Executable> userPressActions);

}
