package game;

import java.util.List;

import gameElements.ISprite;
import authoringEnvironment.Settings;
import gameElements.ViewPoint;
import goals.Goal;
import goals.GoalProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;

/**
 * Used for editing the game. Can edit/set/remove goals, game info, current
 * level, keys,the user sprite, and Level Characteristics
 */

public class GameEditor implements IGameEditor {

	private Game myGame;

	public GameEditor() {
		myGame = new Game(new SimpleDoubleProperty(Settings.getScreenWidth()), new SimpleDoubleProperty(Settings.getScreenHeight()));
	}

	public Game getGame() {
		return myGame;
	}

	public void setGame(Game newGame) {
		myGame = newGame;
	}

	public GameInfo getGameInfo() {
		return myGame.getGameInfo();
	}

	public void setGameInfo(GameInfo newInfo) {
		myGame.setGameInfo(newInfo);
	}

	public void createLevel(int index, LevelProperties levelProperties) {
		myGame.createLevel(index, levelProperties);
	}

	public void addLevel(int index, Level level) {
		myGame.addLevel(index, level);
	}

	public void setCurrentLevel(int index) {
		myGame.setCurrentLevel(index);
	}

	public Level getCurrentLevel() {
		return myGame.getCurrentLevel();
	}

	public void addGoal(Goal newGoal) {
		myGame.getCurrentLevel().addGoal(newGoal);
	}

	public void deleteGoal(Goal goal) {
		myGame.getCurrentLevel().deleteGoal(goal);
	}

	public void setLevelProperties(LevelProperties levelProperties) {
		myGame.getCurrentLevel().setLevelProperties(levelProperties);
	}

	public Integer getUserSprite() {
		return myGame.getCurrentLevel().getCurrentSpriteID();
	}

	public void setSpriteActions() {
		//myGame.getCurrentLevel().setSpriteActions();
	}
	
	public void setUserSprite(Integer sprite) {
		myGame.getCurrentLevel().setCurrentSpriteID(sprite);
	}

	public void updateGame() {

		//System.out.println(myGame.getCurrentLevel().getLevelProperties().getShouldRestart().getValue());
		//myGame.getCurrentLevel().getCurrentSprite().kill();
	//System.out.println(myGame.getCurrentLevel().getCurrentSprite().isDead());

		myGame.getCurrentLevel().update();
	// System.out.println(myGame.getCurrentLevel().getCurrentSprite().isDead());
		
		// System.out.println(myGame.getCurrentLevel().getLevelProperties().getShouldRestart().getValue());
		// myGame.getCurrentLevel().getCurrentSprite().kill();
		// System.out.println(myGame.getCurrentLevel().getCurrentSprite().isDead() + "is sprite dead");
		/*if(!myGame.getCurrentLevel().getSpriteMap().getSpriteMap().containsKey(myGame.getCurrentLevel().getCurrentSprite())){
			System.out.println("restart game");
			myGame.restartGame();

		}*/
		if (myGame.getCurrentLevel().getSpriteMap().getUserControlledSprite().isDead()){
	//		System.out.println("restart game");
	// 		does restart game work???
		/*	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("I have a great message for you!");

			alert.showAndWait();
			*/
			myGame.restartGame();
		}

		if (myGame.getCurrentLevel().getisFinished()){
			if(myGame.getCurrentLevel().getLevelProperties().getNextLevel()!=null){
				myGame.nextLevel(myGame.getCurrentLevel().getLevelProperties().getNextLevel());
			}
			myGame.setIsFinished(true);
		}
		//System.out.println(myGame.getIsFinished().toString()+"gameEditor");
		//System.out.println(myGame.getIsFinished());
	}

	/*public void setResultForKeyPress(KeyEvent event) {
		myGame.getCurrentLevel().handleKeyPress(event);
	}*/

}
