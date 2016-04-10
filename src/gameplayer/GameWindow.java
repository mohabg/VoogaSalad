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

	private Screen myPlayScreen;
	private Screen myPauseScreen;
	private GameFileScreen myGameFileScreen;
	private Screen mySettingsScreen;

//	private enum Screens {
//		Play,
//		Pause,
//		GameFile,
//		Settings;
//	};
	

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

		myFactory.makeOneButton("c", p -> {setScreen("GameFileScreen");});
		setScreen("GameFileScreen");
	}
	
	private void makeButtons() {	
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
		
		List<LevelModel> gameLevels = parseAndLoadGame(file);
		PlayScreen ps = (PlayScreen) myScreenMap.get("PlayScreen");
		ps.setGameLevels(gameLevels);
		
		setScreen("PlayScreen");
	}

	private ArrayList<LevelModel> parseAndLoadGame(File file) {
		XStream xstream = new XStream(new StaxDriver());
		FXConverters.configure(xstream);
		return (ArrayList<LevelModel>) xstream.fromXML(file);
	}
	
	public Scene getScene() {
		Scene myRetScene = new Scene(myPane, myScreenWidth, myScreenHeight);
		myRetScene.getStylesheets().add("resources/styles.css");
		return myRetScene;
	}

}
