package gameElements;

import java.awt.Point;
import java.util.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SpawnConditions extends ExecuteConditions{
	
	private DoubleProperty myRespawnProbability;
	//^^This should be kept somewhere where the user can set respawn probability in the authoring environment
	private DoubleProperty myRespawnTopProbability;
	private DoubleProperty myRespawnLeftProbability;
	private DoubleProperty myRespawnRightProbability;

	public SpawnConditions(){
		super();
		myRespawnProbability = new SimpleDoubleProperty(0.5);
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
				if (getDistFromUser() >= getMinDistFromUser() && getDistFromUser() <= getMaxDistFromUser()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Point getSpawnPosition(){
		//use probability to return left right top
		if(Math.random() >= myRespawnProbability.doubleValue()){
			if(Math.random() >= 0 && Math.random() <= myRespawnLeftProbability.doubleValue()){
				//If generates between 0 and 0.3 respawn at top
				//TODO: Replace this with Top of Screen Point
				return new Point(3,4);
			}
			if(Math.random() > myRespawnTopProbability.doubleValue() && Math.random() < myRespawnLeftProbability.doubleValue()){
				//If generates between 0.4 and 0.6 respawn at left position
				//TODO: Replace this with Left of Screen point
				return new Point(3,4);
			}
			if(Math.random() > myRespawnLeftProbability.doubleValue() && Math.random() < myRespawnRightProbability.doubleValue()){
				//if generates greater than 0.6 respawn at right point
				//TODO: Replace this with Right of Screen point
				return new Point(3,4);
			}
			
		}
		
		
		
		return null;
		
	}
}
