package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.LevelModel;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import game.Engine;
import game.GameEditor;
import game.IGameEditor;
import gameElements.Sprite;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import level.Level;
import level.LevelProperties;
import resources.FrontEndData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
<<<<<<< HEAD
 * IScreen for playing the game. Has a HUD.
 * This class also has an instance of Engine, which runs the game loop
=======
 * IScreen for playing the game. Has a HUD. This class also has an instance of
 * Engine, which runs the game loop
 *
>>>>>>> origin/master
 * @author Huijia
 *
 */
public class PlayScreen extends Screen {
	private Map<Level, Map<Integer, ViewSprite>> myViewSprites;
	private Engine myEngine;

	private HeadsUpDisplay myHUD;

//	private List<LevelModel> gameLevels;
	private File gameFile;
	private Level currentLevel;

	public PlayScreen(File newGameFile) {
		super();
		gameFile = newGameFile;
		myViewSprites = new HashMap<Level, Map<Integer, ViewSprite>>();

		myHUD = new HeadsUpDisplay(getScene().getWidth(), getScene().getHeight());
		
		// add above to HUD

	}

	private void initHUD() {
		Button pauseButton = makePauseButton();
//		System.out.println(pauseButton.getOnKeyPressed());
//		System.out.println(getScene().getOnKeyPressed());
//		
//		System.out.println(myPane.getOnKeyPressed());
		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
//		System.out.println(myPane.getOnKeyPressed());
		myHUD.addToHUDElement(HUDEnum.Left, myEngine.getGameTimeInSeconds(), myEngine.getCurrentLevel().getScore().getScoreValue());
		Sprite user = myEngine.getCurrentLevel().getCurrentSprite();
		myHUD.addToHUDElement(HUDEnum.Right, user.getHealth().getProperty());
//		myHUD.addToHUDElement(HUDEnum.Right, user.getCollisions());
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
//		this.gameLevels = gameLevels;

		myEngine = new Engine(this, new GameEditor());

		myViewSprites = GameLoader.makeLevelViewSpriteMap(gameLevels);

		// TODO: go through loop
		myViewSprites.keySet().forEach(level -> myEngine.addLevel(level.getLevelProperties().getLevelID(), level));
		setLevel(myEngine.getCurrentLevel(), null);
		myEngine.gameLoop();

		// TODO GIVE MODELS TO BACKEND


		// bind image-specific attributes
		initHUD();
	}

	public void setLevel(Level newLevel, Level oldLevel) {
		try{
		myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
		}
		catch (Exception e){

		}
		currentLevel = newLevel;
		System.out.println(myPane.getChildren().toString());
		SpriteFactory sf = new SpriteFactory(myPane, myViewSprites.get(newLevel), newLevel.getSpriteMap());
		newLevel.setSpriteFactory(sf);
		
		myPane.removeEventFilter(KeyEvent.KEY_PRESSED, key -> oldLevel.handleKeyPress(key));
		myPane.removeEventFilter(KeyEvent.KEY_RELEASED, key -> oldLevel.handleKeyRelease(key));

		myPane.addEventFilter(KeyEvent.KEY_PRESSED, key -> newLevel.handleKeyPress(key));
		myPane.addEventFilter(KeyEvent.KEY_RELEASED, key -> newLevel.handleKeyRelease(key));

		myPane.getChildren().addAll(myViewSprites.get(newLevel).values());

	}

	// private Group getViewSprites(Map<ViewSprite, Sprite> spriteList){
	//
	// return
	// }

	public File getGameFile() {
		return gameFile;
	}


	public void play() {
		myEngine.playGameLoop();
	}
//	public void setSprites(List<Integer> activeSprites){
//		myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
//		activeSprites.forEach(s->myPane.getChildren().add(myViewSprites.get(currentLevel).get(s)));
//	}

	public void removeSprites(List<Integer> deadSprites) {
		deadSprites.forEach(s -> {
			System.out.println(s);
			myPane.getChildren().remove(myViewSprites.get(currentLevel).get(s));
			myViewSprites.get(currentLevel).remove(s);
		});
	}
	public Level getCurrentLevel(){
		return currentLevel;
	}

}
