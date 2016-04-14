package gameplayer;

import HUD.HUDEnum;
import HUD.HeadsUpDisplay;
import authoringEnvironment.LevelModel;
import authoringEnvironment.RefObject;
import authoringEnvironment.Settings;
import authoringEnvironment.ViewSprite;
import gameElements.Actor;
import gameElements.Behavior;
import gameElements.Engine;
import gameElements.GameEditor;
import gameElements.IGameEditor;
import gameElements.Level;
import gameElements.LevelProperties;
import gameElements.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayScreen implements IScreen {
	private Pane myPane;
	private Map<Level, Group> myViewSprites;

	private Scene myScene;
	private HeadsUpDisplay myHUD;
	
	private File gameFile;
	private IScreen parentScreen;
	
	private final String PAUSE = "Pause";
	
	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		myPane = new Pane();
		Settings.setGamePlayingSettings(myPane);
		myViewSprites = new HashMap<Level, Group>();

		myScene = new Scene(myPane);
		myHUD = new HeadsUpDisplay(myScene.getWidth(), myScene.getHeight());
		initHUD();
		// add above to HUD
	
	}

	private void initHUD() {
		Button pauseButton = makePauseButton();
		myHUD.addToHUDElement(HUDEnum.Up, pauseButton);
		myPane.getChildren().add(myHUD.getHUD());
	}
	
	private Button makePauseButton() {
		return ButtonFactory.makeButton(PAUSE, a -> {
			PauseScreen ps = new PauseScreen();
			ps.setParentScreen(this);
			switchScene(ps);
		});
	}
	
	public void setGameLevels(List<LevelModel> gameLevels) {
		IGameEditor myGameEditor = new GameEditor();
		Engine engine = new Engine(myGameEditor);
		for (int i=0; i<gameLevels.size();i++) {
			LevelModel lm = gameLevels.get(i);
			Level newLevel = makeNewLevel(lm);
			engine.addLevel(i, newLevel);
			
			setLevel(newLevel);
		}
		engine.gameLoop();
		// TODO GIVE MODELS TO BACKEND
		
		
		// bind image-specific attributes
	}

	private Level makeNewLevel(LevelModel lm) {
		Map<ViewSprite, Sprite> spriteList = lm.getMyMap();
		
		Level newLevel= new Level();

		newLevel.setSpriteMap(new HashMap<Integer, Sprite>());
		newLevel.setLevelProperties( new LevelProperties());
		newLevel.setCurrentSpriteID(0);
		Group levelViewSprites = new Group();
		for(ViewSprite vs : spriteList.keySet()) {
			Sprite s = spriteList.get(vs);
			Actor a = new Actor(s.getSpriteProperties(), s.getHealth(), s.getCollisions(), s.getBehaviors(), new RefObject(s.getMyRef()), new HashMap<KeyEvent, Behavior>());
		    vs.xProperty().bindBidirectional(a.getX());
		    vs.yProperty().bindBidirectional(a.getY());
		    
		    levelViewSprites.getChildren().add(vs);
			newLevel.addSprite(a);
		}
		myViewSprites.put(newLevel, levelViewSprites);
		return newLevel;
	}
	private void setLevel(Level newLevel){
		myPane.setOnKeyPressed(key-> newLevel.handleKeyPress(key));
		myPane.setOnKeyReleased(key-> newLevel.handleKeyRelease(key));
		myPane.getChildren().add(myViewSprites.get(newLevel));

	}
	
//	private Group getViewSprites(Map<ViewSprite, Sprite> spriteList){
//		
//		return 
//	}
	
	

	public File getGameFile() {
		return gameFile;
	}
	
	@Override
	public Scene getScene() {
		return myPane.getScene();
	}

	@Override
	public void switchScene(IScreen screen) {
		((Stage) myPane.getScene().getWindow()).setScene(screen.getScene());	
	}

	@Override
	public void setParentScreen(IScreen screen) {
		parentScreen = screen;	
	}
}
