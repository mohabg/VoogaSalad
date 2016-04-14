package game;
import javafx.scene.input.KeyEvent;
import keyboard.IKeyboardAction.KeyboardActions;
import level.Level;
import level.LevelProperties;

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
    	if ( myGame.getCurrentLevel().getisFinished())
    		myGame.nextLevel(myGame.getCurrentLevel().getLevelProperties().getNextLevel());
    }
	
	public void setResultForKeyPress(KeyEvent event) {
    	myGame.getCurrentLevel().handleKeyPress(event);
    }
    
    public void setResultForKeyRelease(KeyEvent event) {
    	myGame.getCurrentLevel().handleKeyRelease(event);
    }
}
