// This entire file is part of my masterpiece.
// HUIJIA YU
//This class is the basis for the actual game. The purpose of my
//masterpiece was to refactor it so in order to add to its flexibility,
//so that it could be used generally without needing to change much
//(following the open closed principle). This class is an example of the 
//mediator design pattern, as well as the controller in the MVC model. 
//The assumption underlying this class's design is that the engine 
//is structured with levels as the main focus.

package gameplayer;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import game.Engine;
import game.GameEditor;
import gameElements.SpawnController;
import gameElements.ISprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import level.Level;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 
 * IScreen for playing the game. Has a HUD. This class also has an instance of
 * Engine, which runs the game loop
 *
 * @author Huijia
 *
 */
public class PlayScreen {
	// private ObservableList<Integer> activeSprites;
	private IEngine myEngine;
	private IView myView;
	private File gameFile;
	// private Level currentLevel;

	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		myView = new PlayerView();
		myEngine = new Engine(new GameEditor());
		setGameLevels(GameLoader.parseAndLoadGame(gameFile));
	}

	public PlayScreen(String name) {
		this(new File(String.format(GameLoader.SAVED_FOLDER_DIRECTORY, name)));
	}


	public void setGameLevels(List<LevelModel> gameLevels) {
		for (int i = 0; i < gameLevels.size(); i++) {
			LevelModel lm = gameLevels.get(i);
			Level newLevel = GameLoader.makeLevel(lm, i);
			int id = newLevel.getLevelProperties().getLevelID();
			myEngine.addLevel(id, newLevel);
			myView.setLevelSprites((Number) id, GameLoader.setLevelSprites(newLevel, lm.getMySpriteList()));
			myView.setBackgroundList(id, lm.getBackground());
		}

		myEngine.getCurrentLevelID().addListener((observable, oldValue, newValue) -> {
			setLevel(newValue);
		});

		setLevel(0);
		myEngine.start();
	}

	public void setLevel(Number newValue) {

		setActiveSpriteListener();
		setKeys();
		setViewLevel(newValue);
		setEngineLevel(newValue);
	}

	private void setActiveSpriteListener() {
		myEngine.getActiveSprites().addListener((ListChangeListener<Integer>) change -> {
			myView.setActiveSprites(myEngine.getActiveSprites());
		});
	}

	private void setKeys() {
		myView.getPane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			myEngine.getCurrentLevel().handleKeyPress(key);
			key.consume();
		});
	}

	private void setEngineLevel(Number newValue) {
		Level currentLevel = myEngine.getCurrentLevel();
		SpriteFactory sf = new SpriteFactory(myView.getViewSprites(), currentLevel.getSpriteMap());
		currentLevel.setAIController(new SpawnController(sf));
		currentLevel.setSpriteFactory(sf);

	}

	private void setViewLevel(Number levelID) {
		myView.selectLevelSprites(levelID);
		myView.setBackground(levelID);

	}
	public void play() {
		myEngine.start();
	}

	public void addSprite(ViewSprite vs) {
		AESpriteFactory sf = new AESpriteFactory();
		myView.getViewSprites().put(myEngine.getCurrentLevel().getCurrentSpriteID() + 1, vs);
		myEngine.getCurrentLevel().addSprite(sf.makeSprite(vs));

	}

	

	public File getGameFile() {
		return gameFile;
	}

	public Level getCurrentLevel() {
		return myEngine.getCurrentLevel();
	}

	public Map<Integer, ISprite> getSprites() {
		return myEngine.getSpriteMap();
	}

	public Map<Integer, Node> getViewSprites() {
		return myView.getViewSprites();
	}

	public Screen getScreen() {
		// TODO Auto-generated method stub
		return (Screen) myView;
	}

	public Engine getEngine() {
		return (Engine) myEngine;
	}


}
