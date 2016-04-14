package gameplayer;

import javafx.scene.Scene;
/**
 * Interface for screens (which have panes with javafx elements)
 * @author Huijia
 *
 */
public interface IScreen {

	Scene getScene();
	/**
	 * Switches to scene the specified screen.
	 * @param screen
	 */
	void switchScene(IScreen screen);
	/**
	 * Sets parent screen.
	 * @param screen
	 */
	void setParentScreen(IScreen screen);
}
