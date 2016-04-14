package goals;

public interface IGoalVisitor {
	
	boolean visit(Goal goal);
	boolean visit(PointsGoal goal);
}
