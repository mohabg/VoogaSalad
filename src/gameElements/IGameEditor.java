package gameElements;
import gameElements.IKeyboardAction.KeyboardActions;
import javafx.scene.input.KeyEvent;

public interface IGameEditor {
	
	public Game getGame();
	public void setGame(Game newGame);
	public GameInfo getGameInfo();
	public void setGameInfo(GameInfo newInfo);
	public void createLevel(int index, LevelProperties levelProperties);
	public void addLevel(int index, Level level);
	public void setCurrentLevel(int index);
	public Level getCurrentLevel();
	//public int addGoal(GoalProperties newGoal);
	//public void updateGoal(Integer goalID, GoalProperties newGoal);
	public void deleteGoal(Integer goalID);
	public void setLevelProperties(LevelProperties levelProperties);
	public Integer getUserSprite();
	public void setUserSprite(Integer spriteID);
	public void updateGame();
	public void setResultForKeyPress(KeyEvent event);
	public void setResultForKeyRelease(KeyEvent event);
}
