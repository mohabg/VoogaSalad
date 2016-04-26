package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.LevelModel;
import authoringEnvironment.ViewSprite;
import game.Engine;
import game.GameEditor;
import gameElements.Sprite;
import gameElements.ViewPoint;
import highscoretable.HighScoreController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
public class PlayScreen extends Screen {
	private Map<Level, Map<Integer, ViewSprite>> myViewSprites;
	private ObservableList<Integer> activeSprites;
	private Engine myEngine;

	private HeadsUpDisplay myHUD;

	private File gameFile;
	private Level currentLevel;

	public PlayScreen(File newGameFile) {
		super();
		gameFile = newGameFile;
		myViewSprites = new HashMap<Level, Map<Integer, ViewSprite>>();

		myHUD = new HeadsUpDisplay(getScene().getWidth(), getScene().getHeight());

	}

	private void initHUD() {
		Button pauseButton = makePauseButton();

		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
		//myHUD.addToHUDElement(HUDEnum.Up, myEngine.getGameTimeInSeconds(), myEngine.getCurrentLevel().getScore());

		myHUD.addToHUDElement(HUDEnum.Up, currentLevel.getCurrentSprite().getHealth().getProperty());
		myPane.getChildren().add(myHUD.getHUD());
	}

	private Button makePauseButton() {
		return ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("pause"), a -> {
			PauseScreen ps = new PauseScreen(this);
			ps.initBorderPane(myViewSprites.keySet());
			switchScene(ps);
			myEngine.pauseGameLoop();
		});
	}

	public void setGameLevels(List<LevelModel> gameLevels) {
		myEngine = new Engine(this, new GameEditor());

		myViewSprites = GameLoader.makeLevelViewSpriteMap(gameLevels);

		myViewSprites.keySet().forEach(level -> myEngine.addLevel(level.getLevelProperties().getLevelID(), level));
		setLevel(myEngine.getCurrentLevel());
		myEngine.gameLoop();

	}

	public void setLevel(Level newLevel) {
		try {
			myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
		} catch (Exception e) {

		}
		currentLevel = newLevel;

		SpriteFactory sf = new SpriteFactory(myViewSprites.get(currentLevel), currentLevel.getSpriteMap());
		currentLevel.setSpriteFactory(sf);
		activeSprites = currentLevel.getSpriteMap().getActiveSprites();
		activeSprites.addListener(new ListChangeListener<Integer>() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				setSprites();
			}
		});

		setKeys();
		setSprites();
		myEngine.getMyEditor().getGame().setViewPoint(58*20, 58*11, currentLevel.getCurrentSprite());
	//	myEngine.getMyEditor().getGame().setViewPoint(new ViewPoint( new SimpleDoubleProperty(), new SimpleDoubleProperty(), myEngine.getCurrentLevel().getCurrentSprite()));
		initHUD();

	}

	public File getGameFile() {
		return gameFile;
	}

	public void play() {
		myEngine.playGameLoop();
	}

	public void setSprites() {
		// System.out.println("printing setsprites"+ currentLevel.getSpriteMap().getSpriteMap().size());
		myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
		activeSprites.forEach(s -> {
			myPane.getChildren().add(myViewSprites.get(currentLevel).get(s));
			});
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public Pane getPane() {
		return myPane;
	}

	public Map<Integer, Sprite> getSprites() {
		return myEngine.getSpriteMap();
	}

	public Map<Integer, ViewSprite> getViewSprites() {
		return myViewSprites.get(currentLevel);
	}

	public void setKeys() {
		myPane.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			currentLevel.handleKeyPress(key);
			key.consume();
		});
		myPane.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
			currentLevel.handleKeyRelease(key);
			key.consume();
		});
	}
	
	public void makeHighScoreTable(){
		HighScoreController hsc = new HighScoreController();
//		hsc.addHighScore(myEngine.getScore(), myEngine.getName());
		hsc.addHighScore(10.1, "game");
		myPane.getChildren().clear();
		myPane.getChildren().add(hsc.getTable());
		
	}
	
	

}
