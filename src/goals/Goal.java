package goals;

/**
 * Describes the goals for the level. 
 */


public abstract class Goal implements IGoal{
	
	public enum Goals{
		PointsGoal,
		StayAliveGoal,
		KillBossGoal
	}
	
	private GoalProperties goalProperties;
	
	public Goal(GoalProperties myProperties){
		setGoalProperties(myProperties);
	}

	public GoalProperties getGoalProperties() {
		return goalProperties;
	}

	public void setGoalProperties(GoalProperties goalProperties) {
		this.goalProperties = goalProperties;
	}
	
	public void setFinished(){
		goalProperties.setFinished();
	}
	
	public void setIsFinished(boolean isFinished){
		goalProperties.setIsFinished(isFinished);
	}
	
	public boolean isFinished(){
		return goalProperties.isFinished();
	}
	public Goals getGoal(){
		return goalProperties.getMyGoal();
	}
	
	public void acceptVisitor(IGoalVisitor visitor){
		setIsFinished(visitor.visit(this));

	}
}
