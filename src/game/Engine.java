package game;

import authoringEnvironment.LiveEditing;
import events.EventManager;
import gameElements.ISprite;
import gameElements.Time;
import goals.Goal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import level.Level;
import level.LevelProperties;

import java.util.Map;

/**
 * Contains the game loop, the gameTime, and the Editor, basically everything that the game authoring environment needs directly 
 * 
 * @author gauravkumar
 */

public class Engine {
	
	private static final double TIME_PER_FRAME = 0.017;// 60 FPS 0.017
	
	private Timeline myGameLoop;
	private EventManager myEventManager;
	private IntegerProperty currentLevelID;
	private IGameEditor myEditor;
	private boolean isPaused;
	private BooleanProperty isGameFinished;
//    private PlayScreen myGameScreen;


	public Engine() {
		myEventManager = new EventManager();
//		this.myGameScreen = myGameScreen;
		currentLevelID = new SimpleIntegerProperty(0);
		myGameLoop = new Timeline();
//		myTimeProperty = new SimpleDoubleProperty(0);
		isPaused = false;
		isGameFinished = new SimpleBooleanProperty(false);
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
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(TIME_PER_FRAME),
                event -> {
                    isGameFinished = myEditor.getGame().getIsFinished();
                    if(isGameFinished.get()){
                    	myGameLoop.stop();
                    	Stage stage = new Stage();
                        StackPane pane = new StackPane();
                        pane.getChildren().add(new Label("Hello world"));
                        stage.setScene(new Scene(pane, 100, 100));
                        stage.showAndWait();
                    }
                    myEditor.updateGame();
                    currentLevelID.set(myEditor.getCurrentLevel().getLevelProperties().getLevelID());

                });
        myGameLoop.setCycleCount(Timeline.INDEFINITE);  
        myGameLoop.getKeyFrames().add(keyFrame);
        myGameLoop.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    	Stage stage = new Stage();
                        StackPane pane = new StackPane();
                        pane.getChildren().add(new Label("Hello world"));
                        stage.setScene(new Scene(pane, 100, 100));
                        stage.showAndWait();
                    	/*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                		alert.setTitle("Game Information");
                		alert.setHeaderText("Game Result");
                		alert.setContentText("Congrats you've won the game bitch");
                		alert.showAndWait(); */                   
                    }
                });

            }
        });
        playGameLoop();
        if ( isPaused )
        	pauseGameLoop();
        if ( isGameFinished.getValue() ) {
        	myGameLoop.stop();
        	/*pauseGameLoop();
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle("Game Information");
        	alert.setHeaderText("Game Result");
        	alert.setContentText("Congrats you've won the game bitch");
        	alert.showAndWait();*/       	
        }
        	
    }

	private void endGame() {
		myGameLoop.stop();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Game Information");
		alert.setHeaderText("Game Result");
		alert.setContentText("Congrats you've won the game bitch");
		alert.showAndWait();
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

}
