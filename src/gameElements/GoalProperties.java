package gameElements;

import gameElements.Goal.Goals;

public class GoalProperties {
	private String goalName;
	private Goals myGoal;
	private boolean isFinished;
	private int totalPoints;

	public GoalProperties(Goals goal){
		setMyGoal(goal);
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
