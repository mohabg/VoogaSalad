package game;

import java.util.List;

import goals.Goal;
import goals.GoalProperties;
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

	public void updateGame() {
		myGame.getCurrentLevel().update();
		if (myGame.getCurrentLevel().getisFinished())
			myGame.nextLevel(myGame.getCurrentLevel().getLevelProperties().getNextLevel());
	}

	/*public void setResultForKeyPress(KeyEvent event) {
		myGame.getCurrentLevel().handleKeyPress(event);
	}*/

}
