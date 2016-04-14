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
	private Map<Level, List<ViewSprite>> myViewSprites;
	private Engine myEngine; 
	private Scene myScene;
	private HeadsUpDisplay myHUD;
	
	private File gameFile;
	private IScreen parentScreen;
	
	private final String PAUSE = "Pause";
	
	public PlayScreen(File newGameFile) {
		gameFile = newGameFile;
		myPane = new Pane();
		Settings.setGamePlayingSettings(myPane);
		myViewSprites = new HashMap<Level, List<ViewSprite>>();

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
		 myEngine = new Engine(myGameEditor);
		for (int i=0; i<gameLevels.size();i++) {
			LevelModel lm = gameLevels.get(i);
			Level newLevel = makeNewLevel(lm);
			myEngine.addLevel(i, newLevel);
			
		}
		myEngine.setCurrentLevel(0);
		setLevel(myEngine.getCurrentLevel());

		myEngine.gameLoop();
		// TODO GIVE MODELS TO BACKEND
		
		
		// bind image-specific attributes
	}

	private Level makeNewLevel(LevelModel lm) {
		Map<ViewSprite, Sprite> spriteList = lm.getMyMap();
		
		Level newLevel= new Level();

		newLevel.setSpriteMap(new HashMap<Integer, Sprite>());
		newLevel.setLevelProperties( new LevelProperties());
		newLevel.setCurrentSpriteID(0);
		List<ViewSprite> levelViewSprites = new ArrayList<ViewSprite>();
		
		for(ViewSprite vs : spriteList.keySet()) {
			Sprite s = spriteList.get(vs);
			System.out.println("SPRITE  "+s.getX().doubleValue()+"  "+s.getY().doubleValue());
			
			s.setAsUserControlled();
//			s.getX().bindBidirectional(vs.xProperty());
//			s.getY().bindBidirectional(vs.yProperty());
			vs.setMySpriteProperties(s.getSpriteProperties());
		    vs.xProperty().bindBidirectional(s.getX());
		    vs.yProperty().bindBidirectional(s.getY());
		    vs.fitHeightProperty().bindBidirectional(s.getHeight());
		    vs.fitWidthProperty().bindBidirectional(s.getWidth());
		    vs.rotateProperty().bindBidirectional(s.getAngle());
		    
		    levelViewSprites.add(vs);
			newLevel.addSprite(s);
			// TODO DO WE NEED THIS
			newLevel.setCurrentSpriteID(0);
		}
		myViewSprites.put(newLevel, levelViewSprites);
		return newLevel;
	}
	private void setLevel(Level newLevel){
		System.out.println(myPane.getChildren().toString());
		myPane.setOnKeyPressed(key-> newLevel.handleKeyPress(key));
		myPane.setOnKeyReleased(key-> { 
			newLevel.handleKeyRelease(key);		
			for(ViewSprite vs : myViewSprites.get(newLevel)) {
				System.out.println(vs.xProperty().doubleValue());
				System.out.println(vs.yProperty().doubleValue());
			}
		});
		
		myPane.getChildren().addAll(myViewSprites.get(newLevel));
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
