package game;
import java.util.List;

import goals.Goal;
import goals.GoalProperties;
import javafx.scene.input.KeyEvent;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;

/**
 * 
 * @author gauravkumar
 *
 */
public interface IGameEditor {
	
	Game getGame();
	void setGame(Game newGame);
	GameInfo getGameInfo();
	void setGameInfo(GameInfo newInfo);
	void createLevel(int index, LevelProperties levelProperties);
	void addLevel(int index, Level level);
	void setCurrentLevel(int index);
	Level getCurrentLevel();
	void addGoal(Goal newGoal);
	void deleteGoal(Goal goal);
	void setLevelProperties(LevelProperties levelProperties);
	Integer getUserSprite();
	void setUserSprite(Integer sprite);
	
    void updateGame();
}
