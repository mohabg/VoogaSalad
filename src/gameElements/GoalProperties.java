package gameElements;

import gameElements.Goal.Goals;

/**
 * The GoalProperties class is fed to the GoalFactory to create a new goal. This class will provide the information to determine
 * when/if a level is and should be finished. 
 */


public class GoalProperties {
	private String goalName;
	private Goals myGoal;
	private boolean isFinished;
	private int totalPoints;

	public GoalProperties(Goals goal){
		setMyGoal(goal);
		setGoalName(goal.toString());
		setIsFinished(false);
		totalPoints=0;
		
	}
	
	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}

	public Goals getMyGoal() {
		return myGoal;
	}

	public void setMyGoal(Goals myGoal) {
		this.myGoal = myGoal;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	

	public void setIsFinished(boolean isCompleted) {
		this.isFinished = isCompleted;
	}
	
	public void setFinished(){
		setIsFinished(false);
	}
	
	public void resetIsFinished(){
		setIsFinished(true);
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
}
