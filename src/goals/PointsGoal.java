package goals;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Describes the amount of points you need to get to the next level. The information is recieved from the 
 * getTotalPoints() method of the goalProperties. 
 */

public class PointsGoal extends Goal implements IGoal{

	private IntegerProperty numPoints;
	
	public PointsGoal(GoalProperties myProperties) {
		super(myProperties);
		setNumPoints();
		
	}
	
	public void setNumPoints(){
        numPoints = new SimpleIntegerProperty();
		numPoints.set(getGoalProperties().getTotalPoints());
	}	
	
	public int getNumPoints(){
		return this.numPoints.getValue();
	}
	
}
