package gameplayer;

import authoringEnvironment.AESpriteFactory;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import game.Engine;
import game.GameEditor;
import gameElements.AIController;
import gameElements.ISprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
	private ObservableList<Integer> activeSprites;
	private Engine myEngine;
	private PlayerView myView;
	private File gameFile;
	private Level currentLevel;

	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		myView = new PlayerView();
		myEngine = new Engine(new GameEditor());
        setGameLevels(GameLoader.parseAndLoadGame(gameFile));
	}
	
	public PlayScreen(String name){
		this(new File(String.format(GameLoader.SAVED_FOLDER_DIRECTORY, name)));
	}

    public void init(){
        setKeys();

    }


	public void setGameLevels(List<LevelModel> gameLevels) {
		// myViewSprites = GameLoader.makeLevelViewSpriteMap(gameLevels);
		for (int i = 0; i < gameLevels.size(); i++) {
			LevelModel lm = gameLevels.get(i);
			Level newLevel = GameLoader.makeLevel(lm, i);
			newLevel.populateGoals();
			System.out.println("playscreen"+newLevel.getLevelProperties().getGoalProperties());
			int id = newLevel.getLevelProperties().getLevelID();
			myEngine.addLevel(id, newLevel);
			myView.setViewSprites(id, GameLoader.setLevelSprites(newLevel, lm.getMySpriteList()));
			myView.setBackgroundList(id, lm.getBackground());
		}
		myEngine.setCurrentLevel(0);
		myEngine.getCurrentLevelID().addListener((observable, oldValue, newValue) -> {
            setLevel();
        });
		setLevel();
		myEngine.gameLoop();
	}

	public void setLevel() {
		myView.clearSprites();
		currentLevel = myEngine.getCurrentLevel();
		System.out.println("setLevel" + currentLevel.getLevelProperties().getGoalProperties());
		myView.setLevelSprites(currentLevel.getLevelProperties().getLevelID());
		SpriteFactory sf = new SpriteFactory(myView.getViewSprites(), currentLevel.getSpriteMap());
		AIController enemyController = new AIController(sf);
		currentLevel.setAIController(enemyController);
		currentLevel.setSpriteFactory(sf);
		activeSprites = currentLevel.getSpriteMap().getActiveSprites();
		activeSprites.addListener((ListChangeListener<Integer>) change -> {
            myView.setSprites(activeSprites);
        });
		myView.setSprites(activeSprites);
		myView.setBackground(currentLevel.getLevelProperties().getLevelID());
		setKeys();
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

	public Map<Integer, ISprite> getSprites() {
		return myEngine.getSpriteMap();
	}

	public Map<Integer, ViewSprite> getViewSprites() {
		return myView.getViewSprites();
	}

	public void setKeys() {
		myView.getPane().addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			System.out.println(key.getCode());
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
	
	public Engine getEngine(){
		return myEngine;
	}

	public DoubleProperty getTime() {
		// TODO Auto-generated method stub
		return currentLevel.getLevelProperties().getTime().getMyCurrentTimeProperty();
	}
	
	public DoubleProperty getHealth() {
		System.out.println(myEngine.getCurrentLevel().getCurrentSprite().getMyHealth().getProperty().doubleValue());
		return myEngine.getCurrentLevel().getCurrentSprite().getMyHealth().getProperty();
	}
	
	public DoubleProperty getScore() {
		System.out.println(myEngine.getCurrentLevel().getScore());
		return myEngine.getCurrentLevel().getScore();
	}

}
