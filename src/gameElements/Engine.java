package gameElements;

public class Engine {
	
	private Game myGame;

	public Engine() {
		myGame = new Game();
	}
	
	public GameInfo gameInfo() {
		return myGame.getGameInfo();
	}
	
	public void setGameInfo(GameInfo newInfo) {
		myGame.setGameInfo(newInfo);
	}
	
	public void addLevel(int index, LevelProperties levelProperties) {
		myGame.createLevel(index, levelProperties);
	}
	
	public void setCurrentLevel(int index){
		myGame.setCurrentLevel(index);
	}

	public Level getCurrentLevel(){
		return myGame.getCurrentLevel();
	}
	
/*	public int addGoal(GoalProperties newGoal) {
		return myGame.getCurrentLevel().setGoal(newGoal);
	}

	public void updateGoal(Integer goalID, GoalProperties newGoal) {
	    myGame.getCurrentLevel().updateGoal(goalID, newGoal);
	}
*/
	public void deleteGoal(Integer goalID) {
	    myGame.getCurrentLevel().deleteGoal(goalID);
	}
	
	public void setLevelCharacteristics(LevelProperties levelProperties) {  
		myGame.getCurrentLevel().setLevelProperties(levelProperties);
	}
	
	public Integer getUserSprite() {
		return myGame.getCurrentLevel().getCurrentSpriteID();
	}

	public void setUserSprite(Integer spriteID) {
		myGame.getCurrentLevel().setCurrentSpriteID(spriteID);
	}
	
}
