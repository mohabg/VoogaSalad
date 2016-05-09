package goals;

//This entire file is part of my masterpiece.
//Srikar Pyda
// I chose this file to be in my masterpiece because it demonstrates the flexibility of the visitor design pattern in performing a function without modifying the structure of the elements being examined.
// The visitor design pattern allows developers to represent a function to be performed on the elements of an object structure. The visitor design pattern promotes flexibility because it allows developers to modify/define new operations without changing the structure/data of elements. 
// This is an interface which contains the methods that the GoalChecker class will implement. Each visit() method contains the relevant goal's algorithm for determining whether the goal has been met.
public interface IGoalVisitor {
	
	boolean visit(PointsGoal goal);
	boolean visit(KillBossGoal goal);
	boolean visit(StayAliveGoal goal);
	boolean visit(TimeGoal goal);
}
