
package gameplayer;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SettingsScreen implements IScreen {
	private Pane myPane;
	private Scene myScene;
	private IScreen parentScreen;
	
	public SettingsScreen() {
		myPane = new Pane();
		myScene = new Scene(myPane);
	}
	
	@Override
	public Scene getScene() {
		return myPane.getScene();
	}

	@Override
	public void switchScene(IScreen screen) {
		((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());	
	}

	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;	
	}

}