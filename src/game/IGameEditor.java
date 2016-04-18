package game;
import java.util.List;

import goals.Goal;
import goals.GoalProperties;
import javafx.scene.input.KeyEvent;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;

public interface IGameEditor {
	
	public Game getGame();
	public void setGame(Game newGame);
	public GameInfo getGameInfo();
	public void setGameInfo(GameInfo newInfo);
	public void createLevel(int index, LevelProperties levelProperties);
	public void addLevel(int index, Level level);
	public void setCurrentLevel(int index);
	public Level getCurrentLevel();
	public void addGoal(Goal newGoal);
	public void deleteGoal(Goal goal);
	public void setLevelProperties(LevelProperties levelProperties);
	public Integer getUserSprite();
	public void setUserSprite(Integer spriteID);
	
	//TODO: suggestions for better implementation?
	public List<Integer> updateGame();
	
	
	public void setResultForKeyPress(KeyEvent event);
	public void setResultForKeyRelease(KeyEvent event);
}
