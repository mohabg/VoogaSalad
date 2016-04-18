package game;

import java.util.List;

import level.Level;
import level.LevelProperties;

import java.util.ArrayList;

/**
 * Can get/set information about the game in this class, including what the current level is and game info. One can create and 
 * delete levels and restart the game. 
 */


public class Game {
	
	private GameInfo myInfo;
	private List<Level> myGameLevels;
	private int currLevelNum;

	public Game(GameInfo gameInfo) {
		myGameLevels = new ArrayList<Level>();
		currLevelNum = 0;
		myInfo = gameInfo;
	}
	
	public Game() {
		myGameLevels = new ArrayList<Level>();
		currLevelNum = 0;
        myInfo = new GameInfo();
    }

	public GameInfo getGameInfo() {
        return myInfo;
    }

    public void setGameInfo(GameInfo newInfo) {
        myInfo = newInfo;
    }
    
    public int getNumLevels() {
    	return myGameLevels.size();
    }
	
	public void nextLevel(int nextLevelNum) {
		currLevelNum = nextLevelNum;
	}
	
	public void setCurrentLevel(int index) {
        if(index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(myGameLevels.get(index) == null) {
            myGameLevels.add(index, new Level());
        }
        currLevelNum = index;
    }
	
	public Level getCurrentLevel() {
    	return myGameLevels.get(currLevelNum);
    }
	
	public void createLevel(int levelID, Level level) {
        myGameLevels.add(levelID, level);
    }
	
	/**
	 * @param levelID The ID that you want your level to have 
	 * @param levelProperties the method uses the properties defined here to create a new level 
	 */
	
	public void createLevel(Integer levelID, LevelProperties levelProperties) {
		Level newLevel = new Level();

        newLevel.setLevelProperties(levelProperties);
    	myGameLevels.add(newLevel);
	}
	
	/**
	 * returns the original level of the game 
	 */

	public void addLevel(int levelID, Level level) {
		myGameLevels.add(levelID, level);
	}
	
	public Level restartGame() {
        return myGameLevels.get(0);
    }

}
