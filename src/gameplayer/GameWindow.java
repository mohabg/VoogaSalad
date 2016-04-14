package gameplayer;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Wrapper for gameplayer screens. Implements IScreen.
 * 
 * @author Huijia
 *
 */
public class GameWindow implements IScreen {

	private Pane myPane;
	private Map<String, Screen> myScreenMap;
	private IScreen parentScreen;

	public GameWindow() {
		myPane = new Pane();

		// myScreenMap = new HashMap<String, Screen>();
		// myScreenMap.put("PlayScreen", new PlayScreen());
		// myScreenMap.put("PauseScreen", new PauseScreen());
		// myScreenMap.put("GameFileScreen", new GameFileScreen());
		// myScreenMap.put("SettingsScreen", new SettingsScreen());
		//
		// myFactory.makeButton("c", p -> {setScreen("GameFileScreen");});
		setScreen("GameFileScreen");
	}

	public void setScreen(String key) {
		myPane.getChildren().clear();
		myPane.getChildren().add(myScreenMap.get(key).getPane());
	}

	// public void restart() {
	// newGame(myGameFile);
	// }

	@Override
	public void switchScene(IScreen screen) {
		((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());
	}

	@Override
	public Scene getScene() {
		return myPane.getScene();
		// Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		// return myRetScene;
	}

	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;
	}

}
