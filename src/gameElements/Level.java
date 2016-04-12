package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Level implements ILevel{
	private LevelProperties levelProperties;
	private Map<Integer, Sprite> spriteMap;
	private Map<Integer, Goal> goalMap;
	private Integer currentSpriteID;
	private GoalFactory goalFactory;
	private int goalCount;
	
	
	public Level(){
		
	}
	
	public Integer getLevelID() {
		return getLevelProperties().getLevelID();
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
	
	private boolean completeGoals(){
		GoalChecker goalChecker = new GoalChecker(this);
		for(Goal goal:goalMap.values()){
			goal.acceptVisitor(goalChecker);
			if(goal.isFinished()) goalCount++;
		}
		return goalCount>= getLevelProperties().getNumGoals();
	}
	
	private void updateSprites(){
		for(Sprite sprite: spriteMap.values()){
			// sprite.update();
			removeDeadSprite(sprite);
		}
	}
	
	private void removeDeadSprite(Sprite sprite){
		if(sprite.isDead()) spriteMap.remove(sprite);
		
	}
	
	private void checkCollisions(){
		CollisionHandler collisionHandler=new CollisionHandler();
		CollisionChecker checker=new CollisionChecker();
		List<Sprite> spriteList=(ArrayList <Sprite>) spriteMap.values();
		
		for(int i=0; i< spriteMap.values().size(); i++){
			for(int j=i+1; j<spriteMap.values().size(); j++){
				
				if(checker.areColliding(spriteList.get(i), spriteList.get(j))){
					
					for(Collision collisionSpriteOne: spriteList.get(i).getCollisions()){
						for(Collision collisionSpriteTwo: spriteList.get(j).getCollisions()){
							
							collisionHandler.applyCollision(collisionSpriteOne, collisionSpriteTwo);

						}

					}
				}
			}
		}
	}
	@Override
	public void update() {
		checkCollisions();
		if(completeGoals()){
			// TODO : win level
			// TODO : go 2 next level
		}
		// iterate through sprites and update them
		
		// check if sprites are dead
		// health has isDead method
		// remove sprite
		
	}
}
