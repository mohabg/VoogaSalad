package goals;

import goals.Goal.Goals;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The GoalProperties class is fed to the GoalFactory to create a new goal. This class will provide the information to determine
 * when/if a level is and should be finished. 
 */


public class GoalProperties {
	private StringProperty goalName;
	private Goals myGoal;
	private BooleanProperty isFinished;
	private IntegerProperty totalPoints;
	private List<Integer> targetID;

	
	public GoalProperties(Goals goal){
        isFinished = new SimpleBooleanProperty();
        totalPoints = new SimpleIntegerProperty();
        goalName = new SimpleStringProperty();
		setMyGoal(goal);
		setGoalName(goal.toString());
		setIsFinished(false);
		totalPoints.set(0);
		targetID=new ArrayList<Integer>();
	}
	
	public String getGoalName() {
		return goalName.getValue();
	}

	public void setGoalName(String goalName) {
		this.goalName.set(goalName);
	}

	public List<Integer> getTargetID() {
		return targetID;
	}

	public void setTargetID(List<Integer> targetID) {
		this.targetID = targetID;
	}

	
	public Goals getMyGoal() {
		return myGoal;
	}

	public void setMyGoal(Goals myGoal) {
		this.myGoal = myGoal;
	}
	
	public boolean isFinished() {
		return isFinished.getValue();
	}
	

	public void setIsFinished(boolean isCompleted) {
		this.isFinished.set(isCompleted);
	}
	
	public void setFinished(){
		setIsFinished(false);
	}
	
	public void resetIsFinished(){
		setIsFinished(true);
	}

	public int getTotalPoints() {
		return totalPoints.getValue();
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints.set(totalPoints);
	}
	
}
