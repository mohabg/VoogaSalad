package gameElements;

import java.util.List;
import java.util.ArrayList;

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
	
	public Level nextLevel(int nextLevelNum) {
		currLevelNum = nextLevelNum;
		return myGameLevels.get(nextLevelNum);
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
	
	public void createLevel(int levelID, LevelProperties levelProperties) {
		Level newLevel = new Level();
        levelProperties.setLevelID(levelID);
        newLevel.setLevelProperties(levelProperties);
    	myGameLevels.add(levelID, newLevel);
	}
	
	public Level restartGame() {
        return myGameLevels.get(0);
    }

}
