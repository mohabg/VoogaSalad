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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import level.Level;
import level.LevelProperties;
import resources.FrontEndData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IScreen for playing the game. Has a HUD. This class also has an instance of
 * Engine, which runs the game loop
 * 
 * @author Huijia
 *
 */
public class PlayScreen extends Screen {
	private Map<Level, Map<Integer, ViewSprite>> myViewSprites;
	private Engine myEngine;
	private HeadsUpDisplay myHUD;

	private List<LevelModel> gameLevels;
	private File gameFile;
	private Level currentLevel;

	public PlayScreen(File newGameFile) {
		super();
		gameFile = newGameFile;
		myViewSprites = new HashMap<Level, Map<Integer, ViewSprite>>();

		myHUD = new HeadsUpDisplay(getScene().getWidth(), getScene().getHeight());
		initHUD();
		// add above to HUD

	}

	private void initHUD() {
		Button pauseButton = makePauseButton();
		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
		myPane.getChildren().add(myHUD.getHUD());
	}

	private Button makePauseButton() {
		return ButtonFactory.makeButton(FrontEndData.ButtonLabels.getString("pause"), a -> {
			PauseScreen ps = new PauseScreen(this);
			ps.initBorderPane(gameLevels);
			switchScene(ps);
			myEngine.pauseGameLoop();
		});
	}

	public void setGameLevels(List<LevelModel> gameLevels) {
		this.gameLevels = gameLevels;
		myEngine = new Engine(this, new GameEditor());

		myViewSprites = GameLoader.makeLevelViewSpriteMap(gameLevels);

		// TODO: go through loop
		myViewSprites.keySet().forEach(level -> myEngine.addLevel(0, level));
		setLevel(myEngine.getCurrentLevel());

		myEngine.gameLoop();
		// TODO GIVE MODELS TO BACKEND

		// bind image-specific attributes
	}

	private void setLevel(Level newLevel) {
		try{
		myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
		}
		catch (Exception e){
			
		}
		currentLevel = newLevel;
		System.out.println(myPane.getChildren().toString());
		SpriteFactory sf = new SpriteFactory(myPane, myViewSprites.get(newLevel), newLevel.getSpriteMap());
		newLevel.setSpriteFactory(sf);
		myPane.setOnKeyPressed(key -> newLevel.handleKeyPress(key));
		myPane.setOnKeyReleased(key -> {
			newLevel.handleKeyRelease(key);
			for (ViewSprite vs : myViewSprites.get(newLevel).values()) {
				System.out.println(vs.xProperty().doubleValue());
				System.out.println(vs.yProperty().doubleValue());
			}
		});
		// myPane.getChildren().
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

	public void removeSprites(List<Integer> deadSprites) {
		deadSprites.forEach(s -> {
			System.out.println(s);
			myPane.getChildren().remove(myViewSprites.get(currentLevel).get(s));
			myViewSprites.get(currentLevel).remove(s);
		});
	}

}
