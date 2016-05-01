package goals;

import java.util.ArrayList;
import java.util.List;

import goals.Goals;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * The GoalProperties class is fed to the GoalFactory to create a new goal. This class will provide the information to determine
 * when/if a level is and should be finished. 
 */


public class GoalProperties {
	private StringProperty goalName;
	private Goals myGoal;
	private BooleanProperty isFinished;
	private IntegerProperty totalPoints;
	private ListProperty<IntegerProperty> targetID;

	
	public GoalProperties(Goals goal){
		setMyGoal(goal);
		setGoalName(goal.toString());
		setIsFinished(false);
		totalPoints=new SimpleIntegerProperty(100);
		
		ObservableList targetIDList = FXCollections.observableList(new ArrayList<IntegerProperty>());
		targetID=new SimpleListProperty<IntegerProperty>(targetIDList);

	}
	
	public GoalProperties(){
		this(Goals.StayAliveGoal);
	}
	
	public GoalProperties(Goals goal, IntegerProperty points){
		this(goal);
		this.setTotalPoints(points.intValue());
	}
	
	public GoalProperties(Goals goal, ListProperty<IntegerProperty> targets){
		this(goal);
		targetID=targets;
		
	}
	
	public StringProperty getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		StringProperty name=new SimpleStringProperty(goalName);
		this.goalName = name;
	}

	public ListProperty<IntegerProperty> getTargetID() {
		return targetID;
	}

	public void setTargetID(ListProperty<IntegerProperty> targetID) {
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
		BooleanProperty finish= new SimpleBooleanProperty(isCompleted);
		this.isFinished = finish;
	}
	
	public void setFinished(){
		setIsFinished(false);
	}
	
	public void resetIsFinished(){
		setIsFinished(true);
	}

	public int getTotalPoints() {
		return totalPoints.intValue();
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = new SimpleIntegerProperty(totalPoints);
	}
	
}
