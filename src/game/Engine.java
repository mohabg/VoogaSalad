package game;

import javafx.util.Duration;
import level.Level;
import level.LevelProperties;
import gameElements.Time;

import java.util.Map;

import authoringEnvironment.LiveEditing;
import events.EventManager;
import gameElements.ISprite;
import gameElements.Sprite;
import gameplayer.PlayScreen;
import goals.Goal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Contains the game loop, the gameTime, and the Editor, basically everything that the game authoring environment needs directly 
 * 
 */

public class Engine {
	
	private static final double TIME_PER_FRAME = 0.017;// 60 FPS
	
	private Timeline myGameLoop;
	private EventManager myEventManager;
	private IntegerProperty currentLevelID;
	private IGameEditor myEditor;
	private boolean isPaused;
//    private PlayScreen myGameScreen;


	public Engine() {
		myEventManager = new EventManager();
//		this.myGameScreen = myGameScreen;
		currentLevelID = new SimpleIntegerProperty(0);
		myGameLoop = new Timeline();
//		myTimeProperty = new SimpleDoubleProperty(0);
		isPaused = false;
	}
	
	public Engine(IGameEditor editor) {
		this();
		myEditor = editor;
	}
	public IGameEditor getMyEditor() {
		return myEditor;
	}

	public void setMyEditor(IGameEditor myEditor) {
		this.myEditor = myEditor;
	}

	public Engine(LiveEditing liveEditing, GameEditor gameEditor) {
		// TODO Auto-generated constructor stub
	}

	public void setGameInfo(GameInfo gameInfo) {
        myEditor.setGameInfo(gameInfo);
    }
	
	public Map<Integer, ISprite> getSpriteMap(){
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
        //myEventManager = myEditor.getCurrentLevel().getMyEventManager();
    }

    public void getNumLevels() {
        myEditor.getGame().getNumLevels();
    }
    
    public Integer getUserSprite() {
        return myEditor.getUserSprite();
    }

    public void setSpriteActions() {
        myEditor.setSpriteActions();
    }
    
    public void setUserSprite(Integer sprite) {
    	myEditor.setUserSprite(sprite);
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
    //	System.out.println("game loop"+ myEditor.getCurrentLevel().getGoalList().size()); 
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(TIME_PER_FRAME), 
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    myEditor.updateGame();
             //       myEditor.getGame().getViewPoint().updateViewPoint(getCurrentLevel());
                    currentLevelID.set(myEditor.getCurrentLevel().getLevelProperties().getLevelID());
                   
                }
            }); 
        myGameLoop.setCycleCount(Timeline.INDEFINITE);  
        myGameLoop.getKeyFrames().add(keyFrame);
        playGameLoop();
        if ( isPaused )
        	pauseGameLoop();
    }
    
    public void pauseGameLoop() {
    	isPaused = true;
    	myGameLoop.pause();
    }
    
    public void playGameLoop() {
    	isPaused = false;
    	myGameLoop.play();
    }

    
    public IntegerProperty getCurrentLevelID(){
    	return currentLevelID;
    }
    /*public void setResultForKeyPress(KeyEvent event) {
    	myEditor.setResultForKeyPress(event);
    }*/

}
