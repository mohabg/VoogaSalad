package gameElements;

import java.util.Map;

public class Level {
	private Integer levelID;
	private LevelProperties levelProperties;
	private Map<Integer, Sprite> spriteMap;
	private Integer currentSpriteID;
	private GoalFactory goalFactory;
	
	public Level(){
		
	}
	
	public Integer getLevelID() {
		return levelID;
	}

	public void setLevelID(Integer levelID) {
		this.levelID = levelID;
	}

	public LevelProperties getLevelProperties() {
		return levelProperties;
	}

	public void setLevelProperties(LevelProperties levelProperties) {
		this.levelProperties = levelProperties;
	}

	public Map<Integer, Sprite> getSpriteMap() {
		return spriteMap;
	}

	public void setSpriteMap(Map<Integer, Sprite> spriteMap) {
		this.spriteMap = spriteMap;
	}

	public Integer getCurrentSpriteID() {
		return currentSpriteID;
	}

	public void setCurrentSpriteID(Integer currentSpriteID) {
		this.currentSpriteID = currentSpriteID;
	}

	public void deleteSprite(Integer spriteID){
		getSpriteMap().remove(spriteID);
	}
	
	public Integer newSpriteID(Map spriteMap){
		while(spriteMap.keySet().contains(currentSpriteID)) currentSpriteID++;
		return currentSpriteID;
	}
	
	public void addSprite(Sprite newSprite){
		Integer newSpriteID=newSpriteID(spriteMap);
		getSpriteMap().put(newSpriteID, newSprite);
		//return new ID??
		// checking for whether it is the main character-->should be done through the states pattern
	}
	
	public void updateSpriteID(Integer spriteID, Sprite newSprite){
		getSpriteMap().put(spriteID, newSprite);
	}
	
	public int getCurrentPoints(){
		return getLevelProperties().getCurrentPoints();
	}

	public GoalFactory getGoalFactory() {
		return goalFactory;
	}

	public void setGoalFactory(GoalFactory goalFactory) {
		this.goalFactory = goalFactory;
	}
}
