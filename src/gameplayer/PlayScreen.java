package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import game.Engine;
import game.GameEditor;
import gameElements.Sprite;
import highscoretable.HighScoreController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import level.Level;
import resources.FrontEndData;

import java.io.File;
import java.util.HashMap;
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
	private ObservableList<Integer> activeSprites;
	private Engine myEngine;
	private PlayerView myView;
	private File gameFile;
	private Level currentLevel;

	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		myView = new PlayerView();
		myEngine = new Engine(this, new GameEditor());
		setGameLevels(GameLoader.parseAndLoadGame(gameFile));
		

	}
	public PlayScreen(String name){
		this(new File(String.format(GameLoader.SAVED_FOLDER_DIRECTORY, name)));
	}

	public void setGameLevels(List<LevelModel> gameLevels) {

		// myViewSprites = GameLoader.makeLevelViewSpriteMap(gameLevels);
		for (int i = 0; i < gameLevels.size(); i++) {
			LevelModel lm = gameLevels.get(i);
			Level newLevel = GameLoader.makeLevel(lm, i);
			myEngine.addLevel(newLevel.getLevelProperties().getLevelID(), newLevel);
			myView.setViewSprites(GameLoader.setLevelSprites(newLevel, lm.getMySpriteList()));
			myView.setBackground(lm.getBackground());
		}

		myEngine.setCurrentLevel(0);
		setLevel(myEngine.getCurrentLevel());
		myEngine.gameLoop();

	}

	public void setLevel(Level newLevel) {

		currentLevel = newLevel;

		SpriteFactory sf = new SpriteFactory(myView.getViewSprites(), currentLevel.getSpriteMap());
		currentLevel.setSpriteFactory(sf);
		activeSprites = currentLevel.getSpriteMap().getActiveSprites();
		activeSprites.addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				myView.setSprites(activeSprites);
			}
		});

		setKeys();
		myView.setSprites(activeSprites);

	}

	public File getGameFile() {
		return gameFile;
	}

	public void play() {
		myEngine.playGameLoop();
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public Map<Integer, Sprite> getSprites() {
		return myEngine.getSpriteMap();
	}

	public Map<Integer, ViewSprite> getViewSprites() {
		return myView.getViewSprites();
	}

	public void setKeys() {
		myView.getPane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			currentLevel.handleKeyPress(key);
			key.consume();
		});
		myView.getPane().addEventFilter(KeyEvent.KEY_RELEASED, key -> {
			currentLevel.handleKeyRelease(key);
			key.consume();
		});
	}

	public void addSprite(ViewSprite vs) {
		AESpriteFactory sf = new AESpriteFactory();
		myView.getViewSprites().put(currentLevel.getCurrentSpriteID() + 1, vs);
		currentLevel.addSprite(sf.makeSprite(vs));

	}
	public Screen getScreen() {
		// TODO Auto-generated method stub
		return myView;
	}

}
