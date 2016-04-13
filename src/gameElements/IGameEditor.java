package gameElements;

public interface IGameEditor {
	
	public Game getGame();
	public void setGame(Game newGame);
	public GameInfo getGameInfo();
	public void setGameInfo(GameInfo newInfo);
	public void addLevel(int index, LevelProperties levelProperties);
	public void setCurrentLevel(int index);
	public Level getCurrentLevel();
	//public int addGoal(GoalProperties newGoal);
	//public void updateGoal(Integer goalID, GoalProperties newGoal);
	public void deleteGoal(Integer goalID);
	public void setLevelCharacteristics(LevelProperties levelProperties);
	public Integer getUserSprite();
	public void setUserSprite(Integer spriteID);
	public void step();
	public void updateGame();
}
