package game;

import java.util.List;

import gameElements.ViewPoint;
import goals.Goal;
import goals.GoalProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
		myGame = new Game();
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

	public void setUserSprite(Integer spriteID) {
		myGame.getCurrentLevel().setCurrentSpriteID(spriteID);
	}

	// public List<Integer> updateGame() {
	public void updateGame() {
		//System.out.println(myGame.getCurrentLevel().getGoalList().size() + "size of goal list");
		// List<Integer> dead =
		Level level=myGame.getCurrentLevel();
		level.update();
		
		//System.out.println(this.getGame().getViewPoint());
		this.getGame().getViewPoint().updateViewPoint(level);
//		viewPoint.updateViewPoint(level);
//		if (myGame.getCurrentLevel().getisFinished())
//			myGame.nextLevel(myGame.getCurrentLevel().getLevelProperties().getNextLevel());
		// return dead;
	}

	public void setResultForKeyPress(KeyEvent event) {
		myGame.getCurrentLevel().handleKeyPress(event);
	}

	public void setResultForKeyRelease(KeyEvent event) {
		myGame.getCurrentLevel().handleKeyRelease(event);
	}
}
