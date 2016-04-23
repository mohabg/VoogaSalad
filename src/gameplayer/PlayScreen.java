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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private ObservableList<Integer> activeSprites;
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
		myHUD.addToHUDElement(HUDEnum.Up, myEngine.getGameTimeInSeconds(), myEngine.getCurrentLevel().getScore());
		Sprite user = myEngine.getCurrentLevel().getCurrentSprite();
		myHUD.addToHUDElement(HUDEnum.Up, user.getHealth().getProperty());
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

		initHUD();
	}

	public void setLevel(Level newLevel, Level oldLevel) {
		try{
		myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
		}
		catch (Exception e){

		}
		SpriteFactory sf = new SpriteFactory(myViewSprites.get(newLevel), newLevel.getSpriteMap());
		newLevel.setSpriteFactory(sf);
//		activeSprites = newLevel.getSpriteMap().getActiveSprites();
//		activeSprites.addListener(new ListChangeListener<Integer>() {
//		      @Override
//		      public void onChanged(ListChangeListener.Change change) {
//		    	  setSprites();
//		      }});
//		System.out.println(activeSprites.get(0));
//		myPane.removeEventFilter(KeyEvent.KEY_PRESSED, key -> oldLevel.handleKeyPress(key));
//		myPane.removeEventFilter(KeyEvent.KEY_RELEASED, key -> oldLevel.handleKeyRelease(key));

		myPane.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
			newLevel.handleKeyPress(key);
			key.consume();
		});
		myPane.addEventFilter(KeyEvent.KEY_RELEASED, key -> {
			newLevel.handleKeyRelease(key);
			key.consume();
		});
		currentLevel = newLevel;
		myPane.getChildren().addAll(myViewSprites.get(currentLevel).values());
		currentLevel.setCurrentSpriteID(0);
		currentLevel.getCurrentSprite().setAsUserControlled();
		System.out.println(gameFile.getAbsolutePath());
//		setSprites();
		
		

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
	
	public void setSprites(){
		
		
		Map m = myViewSprites.get(currentLevel);
		myPane.getChildren().removeAll(myViewSprites.get(currentLevel).values());
		System.out.println(activeSprites.get(0));
		System.out.println(m.get(activeSprites.get(0)));
		myPane.getChildren();
//		myPane.getChildren().addAll(activeSprites.stream().map(a->myViewSprites.get(currentLevel).get(a)).collect(Collectors.toList()));
//		myPane.getChildren().add((Node) m.get(activeSprites.get(0)));
		activeSprites.forEach(s->{
			System.out.println(s);
//			myPane.getChildren()
//			.add(myViewSprites.get(currentLevel).get(s));
		});
				
	}

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
	
	public Pane getPane(){
		return myPane;
	}
	
	public Map<Integer, Sprite> getSprites(){
		return myEngine.getSpriteMap();
	}
	public Map <Integer, ViewSprite> getViewSprites(){
		return myViewSprites.get(currentLevel);
	}
	public void setKeys(){

		((Stage) myPane.getScene().getWindow()).addEventFilter(KeyEvent.KEY_PRESSED, key -> currentLevel.handleKeyPress(key));
		((Stage) myPane.getScene().getWindow()).addEventFilter(KeyEvent.KEY_RELEASED, key -> currentLevel.handleKeyRelease(key));

	}

}
