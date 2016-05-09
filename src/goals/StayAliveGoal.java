package goals;
//This entire file is part of my masterpiece.
//Srikar Pyda
// This class is an example of my most recent refactoring because it utilizes compositional logic to delegate functionality to closed classes as opposed to utilizing inheritance logic.
// Previously, every goal had to extend from the goal super-class. Now, goals are defined compositionally by having an instance of goalProperties they can access.
// GoalProperties is a closed class because it initializes, maintains, and updates its own information without any dependencies with the Game Engine.
// Although the original design of the goals framework utilized an inheritance hierarchy,
// after completing my analysis I came to the conclusion that the utilization of composition while implementing an interface
// provided for a robust 
// The StayAliveGoal implements the IGoal interface without extending from the abstract goal hierarchy. The new design pattern for 
// initializing and managing Goals involves four distinct patterns we have learned in class: the state design pattern (enumerations in goal properties), factory design pattern (goal factory), visitor design pattern, and composability design architecture.



public class StayAliveGoal implements IGoal{
	private GoalProperties goalProperties;

	public StayAliveGoal(GoalProperties myProperties) {
		goalProperties=myProperties;
		
	}
	
	public StayAliveGoal(){
		goalProperties=new GoalProperties(GoalEnum.StayAliveGoal);
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
