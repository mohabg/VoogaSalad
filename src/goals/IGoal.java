package goals;

//This entire file is part of my masterpiece.
//Srikar Pyda
// I chose this file to be in my masterpiece because I believe it demonstrates the flexibility of the visitor design pattern.
// This interface combines well with the new compositional structure of goals in having a good design.
// My implementation of the compositional design pattern allowed for sufficient flexibility and modularization.
// My implementation of the IGoal interface in addition to the compositional design pattern adds an abstraction of functionality (which in this case is checking goals)

public interface IGoal {

	void acceptVisitor(IGoalVisitor visitor);
	Boolean isFinished();
}
