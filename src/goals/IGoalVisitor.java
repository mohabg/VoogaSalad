package goals;

public interface IGoalVisitor {
	
	boolean visit(PointsGoal goal);
	boolean visit(KillBossGoal goal);
	boolean visit(StayAliveGoal goal);
	boolean visit(TimeGoal goal);
}
