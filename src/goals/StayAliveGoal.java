package goals;

public class StayAliveGoal extends Goal implements IGoal{

	public StayAliveGoal(GoalProperties myProperties) {
		super(myProperties);
	}
	
	public StayAliveGoal(){
		super(new GoalProperties(GoalEnum.StayAliveGoal));
	}
	@Override
	public void acceptVisitor(IGoalVisitor visitor){
		setIsFinished(visitor.visit(this));
	}

}
