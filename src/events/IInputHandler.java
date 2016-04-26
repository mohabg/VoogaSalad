package events;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface IInputHandler {	
	public void mouseClickEvent(MouseEvent event);
	public void keyPressEvent(KeyEvent event);
	public void keyReleaseEvent(KeyEvent event);

}
