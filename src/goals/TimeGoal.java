package goals;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TimeGoal implements IGoal{
private DoubleProperty endTime;
private GoalProperties goalProperties;
	
	public TimeGoal(GoalProperties myProperties){
		goalProperties=myProperties;
		endTime = new SimpleDoubleProperty(0);
	}
	
	public TimeGoal(){
		this(new GoalProperties(GoalEnum.TimeGoal));
	}
	
	public void acceptVisitor(IGoalVisitor visitor){
		getGoalProperties().setIsFinished(visitor.visit(this));
	}

	public DoubleProperty getEndTime() {
		return endTime;
	}

	public void setEndTime(DoubleProperty endTime) {
		this.endTime = endTime;
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
