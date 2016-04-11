package gameplayer;

import javafx.scene.Scene;

public interface IScreen {
	
	Scene getScene();
	
	void switchScene(IScreen screen);
	
	void setParentScreen(IScreen screen);
}
