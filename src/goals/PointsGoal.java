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

	public PointsGoal(){
		super(new GoalProperties(GoalEnum.PointsGoal));
		setNumPoints();
	}
	
	
	public void setNumPoints(){
		numPoints=new SimpleIntegerProperty(getGoalProperties().getTotalPoints());
	}	
	
	public IntegerProperty getNumPoints(){
		return this.numPoints;
	}
	@Override
	public void acceptVisitor(IGoalVisitor visitor){

		setIsFinished(visitor.visit(this));
		

	}
	
}
