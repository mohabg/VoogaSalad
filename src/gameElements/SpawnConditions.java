package gameElements;

import java.awt.Point;
import java.util.*;

import authoringEnvironment.Settings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SpawnConditions extends ExecuteConditions{
	

	//^^This should be kept somewhere where the user can set respawn probability in the authoring environment
	private DoubleProperty myRespawnTopProbability;
	private DoubleProperty myRespawnLeftProbability;
	private DoubleProperty myRespawnRightProbability;

	public SpawnConditions(){
		super();
	    this.setProbability(0.5);
	    this.setFrequency(10);
		myRespawnTopProbability = new SimpleDoubleProperty(0.3);
		myRespawnLeftProbability = new SimpleDoubleProperty(0.6);
		myRespawnRightProbability = new SimpleDoubleProperty(1);

	}
	@Override
	public boolean isAIReady() {
		if (Math.random() < getProbability()) {
			double elapsedTime = System.currentTimeMillis() - this.getRunningTime();
			if (elapsedTime >= this.getFrequency() * 1000) {
				this.setRunningTime(System.currentTimeMillis());
				return true;
			}
		}
		return false;
	}
	
	private Point getSpawnPosition(){
		//use probability to return left right top
			if(Math.random() >= 0 && Math.random() <= myRespawnLeftProbability.doubleValue()){
				//If generates between 0 and 0.3 respawn at top
				//TODO: Replace this with Top of Screen Point
				return new Point(Settings.getScreenWidth()/2,0);
			}
			if(Math.random() > myRespawnTopProbability.doubleValue() && Math.random() < myRespawnLeftProbability.doubleValue()){
				//If generates between 0.4 and 0.6 respawn at left position
				//TODO: Replace this with Left of Screen point
				return new Point(0,Settings.getScreenHeight()/2);
			}
			if(Math.random() > myRespawnLeftProbability.doubleValue() && Math.random() < myRespawnRightProbability.doubleValue()){
				//if generates greater than 0.6 respawn at right point
				//TODO: Replace this with Right of Screen point
				return new Point(Settings.getScreenWidth(),Settings.getScreenHeight()/2);
			}
	
		return null;
		
	}
	@Override
	public void visit(AIController aiController) {
		if(this.isAIReady()){
			spawnSprite(aiController);
		}
	}
	private void spawnSprite(AIController aiController) {
		Point start = this.getSpawnPosition();
		List<ISprite> spritesToSpawn = aiController.getExecuteConditionToSprites().get(this);
		for(ISprite enemy : spritesToSpawn){
			ISprite cloned = enemy.clone();
			aiController.getSpriteFactory().getSpriteMap().addSprite(cloned);
			cloned.getSpriteProperties().setX(start.getX());
			cloned.getSpriteProperties().setY(start.getY());
		}
	}
}
