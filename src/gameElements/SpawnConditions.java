package gameElements;

import authoringEnvironment.Settings;
import behaviors.Behavior;
import behaviors.Gun;
import behaviors.MoveVertically;
import behaviors.Movement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.awt.*;
import java.util.List;

public class SpawnConditions extends ExecuteConditions{
	
	public SpawnConditions(){
		super();
	    this.setProbability(1);
	    this.setFrequency(5);
	}
	/*
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
	*/
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
		List<ISprite> spritesToSpawn = aiController.getSpritesToSpawn();
		for(ISprite enemy : spritesToSpawn){
			ISprite cloned = aiController.getSpriteFactory().clone(enemy);
			cloned.getSpriteProperties().setX(point.getX());
			cloned.getSpriteProperties().setY(point.getY());
		}
	}
}
