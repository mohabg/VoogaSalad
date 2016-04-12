package gameElements;

public interface IGoalVisitor {
	
	boolean visit(Goal goal);
	boolean visit(PointsGoal goal);
}
