package gameElements;

import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import gameElements.IKeyboardAction.KeyboardActions;
import javafx.scene.input.KeyEvent;

public class Engine {
	
	private static final double TIME_PER_FRAME = 0.017;// 60 FPS
	
	//private Game myGame;
	private Level currentLevel;
	private IGameEditor myEditor;
	private double myGameTime;

	public Engine() {
		//myGame = new Game();
		myGameTime = 0;
	}
	
	public Engine(IGameEditor editor) {
		this();
		myEditor = editor;
	}

	public void setGameInfo(GameInfo gameInfo) {
        myEditor.setGameInfo(gameInfo);
    }
	
	public Map<Integer, Sprite> getSpriteMap(){
        return currentLevel.getSpriteMap();
    }
    public LevelProperties LevelProperties(){
        return currentLevel.getLevelProperties();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void addLevel(Integer levelIndex, LevelProperties levelProperties) {
        myEditor.addLevel(levelIndex, levelProperties);
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
    
    
    
    public void gameLoop() {
    	Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE );
        double startTime = System.currentTimeMillis();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(TIME_PER_FRAME), 
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    myGameTime = (System.currentTimeMillis() - startTime)/1000.0;
                    myEditor.updateGame();
                }
            }); 
        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();
    }
    
    public double getGameTimeInSeconds() {
    	return myGameTime;
    }
    
    public void setResultForKeyPress(KeyEvent event) {
    	myEditor.setResultForKeyPress(event);
    }
    
    public void setResultForKeyRelease(KeyEvent event) {
    	myEditor.setResultForKeyRelease(event);
    }

}
