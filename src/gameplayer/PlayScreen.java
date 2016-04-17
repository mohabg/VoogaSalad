package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.FrontEndData;
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
	private Map<Level, List<ViewSprite>> myViewSprites;
	private Engine myEngine;
	private HeadsUpDisplay myHUD;

	private List<LevelModel> gameLevels;
	private File gameFile;

	public PlayScreen(File newGameFile) {
		super();
		gameFile = newGameFile;
		myViewSprites = new HashMap<Level, List<ViewSprite>>();

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
		});
	}

	public void setGameLevels(List<LevelModel> gameLevels) {
		this.gameLevels = gameLevels;
		IGameEditor myGameEditor = new GameEditor();
		myEngine = new Engine(myGameEditor);
		myViewSprites = GameLoader.makeLevelViewSpriteMap(gameLevels);
		
		//TODO: FIND BETTER WAY TO CONNECT--also this doesn't work but will fix soon
		myViewSprites.keySet().forEach(level->myEngine.addLevel(0, level));
		//should be default?
//		myEngine.setCurrentLevel(0);
		setLevel(myEngine.getCurrentLevel());

		myEngine.gameLoop();
		// TODO GIVE MODELS TO BACKEND

		// bind image-specific attributes
	}


	private void setLevel(Level newLevel) {
		System.out.println(myPane.getChildren().toString());
		myPane.setOnKeyPressed(key -> newLevel.handleKeyPress(key));
		myPane.setOnKeyReleased(key -> {
			newLevel.handleKeyRelease(key);
			for (ViewSprite vs : myViewSprites.get(newLevel)) {
				System.out.println(vs.xProperty().doubleValue());
				System.out.println(vs.yProperty().doubleValue());
			}
		});
//		myPane.getChildren().
		myPane.getChildren().addAll(myViewSprites.get(newLevel));
	}

	// private Group getViewSprites(Map<ViewSprite, Sprite> spriteList){
	//
	// return
	// }

	public File getGameFile() {
		return gameFile;
	}

}
