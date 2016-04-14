package goals;

import level.Level;

public class GoalChecker implements IGoalVisitor{
	
	private Level level;
	
	public GoalChecker(Level myLevel){
		setLevel(myLevel);
	}
	
	
	@Override
	public boolean visit(Goal goal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean visit(PointsGoal goal) {
		return goal.getNumPoints() < getLevel().getCurrentPoints();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
