package goals;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class TimeGoal extends Goal implements IGoal{

	private DoubleProperty endTime;
	
	public TimeGoal(GoalProperties myProperties){
		super(myProperties);
		endTime = new SimpleDoubleProperty(0);
	}
	
	public TimeGoal(){
		this(new GoalProperties(GoalEnum.TimeGoal));
	}
	
	public void acceptVisitor(IGoalVisitor visitor){
		visitor.visit(this);
	}

	public DoubleProperty getEndTime() {
		return endTime;
	}

	public void setEndTime(DoubleProperty endTime) {
		this.endTime = endTime;
	}
}
