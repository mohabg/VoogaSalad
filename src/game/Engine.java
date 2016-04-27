package game;

import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;
import gameElements.Time;

import java.util.Map;

import authoringEnvironment.Project1;
import events.EventManager;
import gameElements.Sprite;
import gameplayer.PlayScreen;
import goals.Goal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Contains the game loop, the gameTime, and the Editor, basically everything that the game authoring environment needs directly 
 * 
 */

public class Engine {
	
	private static final double TIME_PER_FRAME = 0.017;// 60 FPS
	
	private Timeline myGameLoop;
	private EventManager myEventManager;
//	private Level currentLevel;
	private IGameEditor myEditor;
	private Time myGameTime;
	private DoubleProperty myTimeProperty;
	private boolean isPaused;
    private PlayScreen myGameScreen;


	public Engine(PlayScreen myGameScreen) {
		myEventManager = new EventManager();
		this.myGameScreen = myGameScreen;
		myGameLoop = new Timeline();
		myTimeProperty = new SimpleDoubleProperty(0);
		isPaused = false;
	}
	
	public Engine(PlayScreen myGameScreen, IGameEditor editor) {
		this(myGameScreen);
		myEditor = editor;
	}

	public Engine(Project1 liveEditing, GameEditor gameEditor) {
		// TODO Auto-generated constructor stub
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
    	myGameTime = new Time();
    //	System.out.println("game loop"+ myEditor.getCurrentLevel().getGoalList().size()); 
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(TIME_PER_FRAME), 
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                	myGameTime.updateTime();
                    myEditor.updateGame();
					if(!myEditor.getCurrentLevel().equals(myGameScreen.getCurrentLevel())){
						myGameScreen.setLevel(myEditor.getCurrentLevel());
					}
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
    
    public double getGameTimeInSeconds() {
    	return myGameTime.getTime()/1000;
}

    public DoubleProperty getTimeProperty() {
    	return myTimeProperty;
    }
    
    /*public void setResultForKeyPress(KeyEvent event) {
    	myEditor.setResultForKeyPress(event);
    }*/

}
