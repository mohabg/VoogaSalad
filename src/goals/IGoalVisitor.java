package goals;

public interface IGoalVisitor {
	
	boolean visit(Goal goal);
	boolean visit(PointsGoal goal);
	boolean visit(KillBossGoal goal);
	boolean visit(StayAliveGoal goal);
}
