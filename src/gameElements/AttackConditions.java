package gameElements;

import java.awt.Point;
import java.util.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class AttackConditions extends ExecuteConditions{

	private DoubleProperty myAttackProbability;
	
	public AttackConditions(){
		super();
		myAttackProbability = new SimpleDoubleProperty(0.5);
	}
	
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
	
	
	public boolean doesShoot(){
		//use probability to return left right top
		if(Math.random() >= myAttackProbability.doubleValue()){
			return true;
		}
		return false;
	}
	
}
