package game;

import gameElements.Sprite;
import gameplayer.PlayScreen;
import goals.Goal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import level.Level;
import level.LevelProperties;

import java.util.Map;

/**
 * Contains the game loop, the gameTime, and the Editor, basically everything that the game authoring environment needs directly 
 * 
 */

public class Engine {
	
	private static final double TIME_PER_FRAME = 0.017;// 60 FPS
	
	private Timeline myGameLoop;
	private Level currentLevel;
	private IGameEditor myEditor;
	private DoubleProperty myGameTime;
	private BooleanProperty isPaused;
    private PlayScreen myGameScreen;


	public Engine(PlayScreen myGameScreen) {
		this.myGameScreen = myGameScreen;
		myGameLoop = new Timeline();
        myGameTime = new SimpleDoubleProperty();
        isPaused = new SimpleBooleanProperty();
		myGameTime.set(0);
		isPaused.set(false);
	}
	
	public Engine(PlayScreen myGameScreen, IGameEditor editor) {
		this(myGameScreen);
		myEditor = editor;
	}

	public void setGameInfo(GameInfo gameInfo) {
        myEditor.setGameInfo(gameInfo);
    }
	
	public Map<Integer, Sprite> getSpriteMap(){
        return myEditor.getCurrentLevel().getSpriteMap().getSpriteMap();
    }
    public LevelProperties LevelProperties(){
        return myEditor.getCurrentLevel().getLevelProperties();
    }

    public Level getCurrentLevel() {
        return myEditor.getCurrentLevel();
    }

    public void createLevel(Integer levelIndex, LevelProperties levelProperties) {
        myEditor.createLevel(levelIndex, levelProperties);
    }
    
    public void addLevel(Integer levelIndex, Level level) {
    	myEditor.addLevel(levelIndex, level);
    }

    public void setCurrentLevel(int levelIndex) {
        myEditor.setCurrentLevel(levelIndex);
    }

    public void getNumLevels() {
        myEditor.getGame().getNumLevels();
    }
    
    public Integer getUserSprite() {
        return myEditor.getUserSprite();
    }

    public void setUserSprite(Integer userSprite) {
        myEditor.setUserSprite(userSprite);
    }
    
    public void addGoal(Goal goal) {
    	myEditor.addGoal(goal);
    }
    
    public void deleteGoal(Goal goal) {
    	myEditor.deleteGoal(goal);
    }
    
    /**
	 * Starts the game loop
	 */
    
    public void gameLoop() {
    	myGameLoop.setCycleCount(Timeline.INDEFINITE );
        double startTime = System.currentTimeMillis();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(TIME_PER_FRAME), 
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    myGameTime.set((System.currentTimeMillis() - startTime)/1000.0);
					myGameScreen.removeSprites(myEditor.updateGame());
					if(!myEditor.getCurrentLevel().equals(myGameScreen.getCurrentLevel())){
						myGameScreen.setLevel(myEditor.getCurrentLevel());
					}
                }
            }); 
        myGameLoop.setCycleCount(Timeline.INDEFINITE);  
        myGameLoop.getKeyFrames().add(keyFrame);
        playGameLoop();
        if ( isPaused.getValue() )
        	pauseGameLoop();
    }
    
    public void pauseGameLoop() {
    	isPaused.set(true);
    	myGameLoop.pause();
    }
    
    public void playGameLoop() {
    	isPaused.set(false);
    	myGameLoop.play();
    }
    
    public double getGameTimeInSeconds() {
    	return myGameTime.doubleValue();
    }
    
    public void setResultForKeyPress(KeyEvent event) {
    	myEditor.setResultForKeyPress(event);
    }
    
    public void setResultForKeyRelease(KeyEvent event) {
    	myEditor.setResultForKeyRelease(event);
    }

}
