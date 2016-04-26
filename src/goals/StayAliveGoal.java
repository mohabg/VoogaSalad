package goals;

public class StayAliveGoal extends Goal implements IGoal{

	public StayAliveGoal(GoalProperties myProperties) {
		super(myProperties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void acceptVisitor(IGoalVisitor visitor){
		//System.out.println("accept stay alive visitor" + visitor.visit(this));
		setIsFinished(visitor.visit(this));

		//System.out.println("reached accept visitor" + getGoalProperties().isFinished());
	}

}
