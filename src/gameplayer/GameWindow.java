package gameplayer;

import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameWindow {
	// Game myGame;
	// GameView myGameView;
	// GameLoader myLoader;
	Pane myPane;
	ButtonFactory myFactory;
	File myGameFile;

	Map<String, Screen> myScreenMap;

	Screen myPlayScreen;
	Screen myPauseScreen;
	GameFileScreen myGameFileScreen;
	Screen mySettingsScreen;

	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	public GameWindow() {

		// Game game
		// myGame = game;
		myPane = new Pane();
		myFactory = new ButtonFactory(this);

		myScreenMap = new HashMap<String, Screen>();
		myScreenMap.put("PlayScreen", new PlayScreen(myFactory));
		myScreenMap.put("PauseScreen", new PauseScreen(myFactory));
		myScreenMap.put("GameFileScreen", new GameFileScreen(myFactory));
		myScreenMap.put("SettingsScreen", new SettingsScreen(myFactory));

		setScreen("GameFileScreen");

	}

	public void setScreen(String key) {
		myPane.getChildren().clear();
		myPane.getChildren().add(myScreenMap.get(key).getPane());
	}

	public void restart() {

		newGame(myGameFile);
	}

	public void newGame(File file) {
		// make game;
		myGameFile = file;
		setScreen("PlayScreen");
	}

	public Scene getScene() {
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

}
