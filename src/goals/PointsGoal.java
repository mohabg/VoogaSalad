package goals;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Describes the amount of points you need to get to the next level. The information is recieved from the 
 * getTotalPoints() method of the goalProperties. 
 */

public class PointsGoal implements IGoal{

	private IntegerProperty numPoints;
	private GoalProperties goalProperties;
	public PointsGoal(GoalProperties myProperties) {
		goalProperties=myProperties;
		setNumPoints();		
	}

	public PointsGoal(){
		goalProperties=new GoalProperties(GoalEnum.PointsGoal);
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
		getGoalProperties().setIsFinished(visitor.visit(this));
		

	}

	public GoalProperties getGoalProperties() {
		return goalProperties;
	}

	public void setGoalProperties(GoalProperties goalProperties) {
		this.goalProperties = goalProperties;
	}
	
	@Override
	public Boolean isFinished() {
		return this.getGoalProperties().isFinished();
	}
}
