package gameElements;

import authoringEnvironment.Settings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.awt.*;
import java.util.List;

public class EnemySpawnConditions extends ExecuteConditions{
	

	//^^This should be kept somewhere where the user can set respawn probability in the authoring environment
	private DoubleProperty myRespawnTopProbability;
	private DoubleProperty myRespawnLeftProbability;
	private DoubleProperty myRespawnRightProbability;

	public EnemySpawnConditions(){
		super();
	    this.setProbability(1);
	    this.setFrequency(3);
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
		int newX = (int) (Math.random() * 0.4* Settings.getScreenWidth());
		return new Point(newX, 0);
	}
	@Override
	public void visit(AIController aiController) {
		if(this.isAIReady()){
			spawnSprite(aiController, this.getSpawnPosition());
		}
	}
	protected void spawnSprite(AIController aiController, Point point) {
		List<ISprite> spritesToSpawn = aiController.getExecuteConditionToSprites().get(this);
		for(ISprite enemy : spritesToSpawn){
			ISprite cloned = aiController.getSpriteFactory().clone(enemy);
			cloned.getSpriteProperties().setX(point.getX());
			cloned.getSpriteProperties().setY(point.getY());
		}
	}
}
