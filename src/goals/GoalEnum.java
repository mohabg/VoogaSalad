package goals;
//This entire file is part of my masterpiece.

//Srikar Pyda
//In order to easily identify goals and simplify the reflection required in the GoalFactory class, each "type" of goal (sub-class of the abstract goal class) is assigned its own Goal Enumeration.The Goal Enumeration is utilized as part of the State Design Pattern.
// This is part of my masterpiece because it is required for my implementation of the state design pattern as a centralized interface for instantiating goals.
public enum GoalEnum {
	PointsGoal, StayAliveGoal, KillBossGoal, TimeGoal;
}
