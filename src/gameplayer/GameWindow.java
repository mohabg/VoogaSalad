package gameplayer;

import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import authoringEnvironment.LevelModel;
import exampledata.XStreamHandlers.FXConverters;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import loading.GameLoader;

public class GameWindow {
	// Game myGame;
	// GameView myGameView;
	// GameLoader myLoader;
	private static final int myScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static final int myScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	private Pane myPane;
	ButtonFactory myFactory;
	private File myGameFile;

	private Map<String, Screen> myScreenMap;

	// private Screen myPlayScreen;
	// private Screen myPauseScreen;
	// private GameFileScreen myGameFileScreen;
	// private Screen mySettingsScreen;
	private GameLoader myGameLoader;
	// private enum Screens {
	// Play,
	// Pause,
	// GameFile,
	// Settings;
	// };

	public GameWindow() {
		// Game game
		// myGame = game;
		myPane = new Pane();
		myFactory = new ButtonFactory(this);
		myGameLoader = new GameLoader();

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

		List<LevelModel> gameLevels = myGameLoader.parseAndLoadGame(file);
		for (LevelModel lm : gameLevels) {
			myScreenMap.get("PlayScreen").addAll(myGameLoader.getViewSprites(lm));
		}

		setScreen("PlayScreen");
	}

	public Scene getScene() {
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

}
