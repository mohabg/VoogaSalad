package goals;

import java.util.ArrayList;
import java.util.List;

import goals.GoalEnum;

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
// added comment for commit for pull request 
//This entire file is part of my masterpiece.
//Srikar Pyda
// After refactoring the Goal Design Framework, I have utilized four design techniques in order to minimize dependencies within the code.
// 1. Utilization of States Design Pattern through an instance of the Goal Enumeration myGoal 
//The utilization of the States Design Pattern also allows for flexibility in the design of Goal functionality; the framework for populating goals through a reflective factory from its enumeration allows for complete variability in the structure of a Goal within the design framework. 
// 2. Factory Design Pattern
// The States Design Pattern facilitates the creation of a GoalFactory object, which implements the Factory design pattern in creating a centralized interface to create goals without exposing the creation-logic to the public.
// 3. Visitor Design Pattern
// The visitor design pattern allows developers to represent a function to be performed on the elements of an object structure. The visitor design pattern promotes flexbility because it allows developers to modify/define new operations without changing the structure/data of elements. 
// This pattern promotes flexibility because it enables an abstraction of functionality (in this case checking goals). Rather than having the elements themselves check functionality, elements are designed to be simpler in this pattern. New functionality can be early added by created a new Visitor sub-class as opposed to having to change the structure of the element.
// 4. **RECENT** Compositional Design Pattern **REFACTORING**
// All goals previously extended from the abstract goal hierarchy which did not allow for flexibility in form of the goal.
// Composition facilitates greater flexibility and modularity in functionality of the feature; rather than creating dependencies in interfaces between the super and subclasses, composition delegates functionality towards closed classes.
// As a result, I believe that this implementation of the compositional design pattern is a better design choice.
// GoalProperties is a closed class because it initializes, maintains, and updates its own information.
/**
 * The GoalProperties class is fed to the GoalFactory to create a new goal. This class will provide the information to determine
 * when/if a level is and should be finished. 
 * 
 */


public class GoalProperties {
	private StringProperty goalName;
	private GoalEnum myGoal;
	private BooleanProperty isFinished;
	private IntegerProperty totalPoints;
	private ListProperty<IntegerProperty> targetID;

	
	public GoalProperties(GoalEnum goal){
		setMyGoal(goal);
		setGoalName(goal.toString());
		setIsFinished(false);
		totalPoints=new SimpleIntegerProperty(100);
		
		ObservableList targetIDList = FXCollections.observableList(new ArrayList<IntegerProperty>());
		targetID=new SimpleListProperty<IntegerProperty>(targetIDList);

	}
	
	public GoalProperties(){
		this(GoalEnum.StayAliveGoal);
	}
	
	public GoalProperties(GoalEnum goal, IntegerProperty points){
		this(goal);
		this.setTotalPoints(points.intValue());
	}
	
	public GoalProperties(GoalEnum goal, ListProperty<IntegerProperty> targets){
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

	
	public GoalEnum getMyGoal() {
		return myGoal;
	}

	public void setMyGoal(GoalEnum myGoal) {
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
