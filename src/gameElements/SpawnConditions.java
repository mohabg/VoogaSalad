package gameElements;

import authoringEnvironment.Settings;
import javafx.beans.property.IntegerProperty;
import java.util.*;
import java.awt.*;
import java.util.List;
public class SpawnConditions extends ExecuteConditions{
	private static final int MY_SCREEN_RANGE = (int) 0.4 * Settings.getScreenWidth();
	public SpawnConditions(){
		super();
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
		int newX = (int) (Math.random() * MY_SCREEN_RANGE);
		return new Point(newX, 0);
	}

    private List<Point> getSpawnPosition(IntegerProperty myObjectY, IntegerProperty myObjectMovement, int myTotalScreenHeight){
        List<Point> myPoints = new ArrayList<>();
        int totalPartitions = myTotalScreenHeight/10;
        int myCurrentScreenY = 0;
        for(int i = 0; i<totalPartitions; i++){
            int myX = (int) (Math.random() * MY_SCREEN_RANGE);
            Point myTempPoint = new Point(myX, myCurrentScreenY);
            myPoints.add(myTempPoint);
        }
        return myPoints;
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
