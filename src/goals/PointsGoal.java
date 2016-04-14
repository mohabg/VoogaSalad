package goals;

public class PointsGoal extends Goal implements IGoal{

	private int numPoints;
	
	public PointsGoal(GoalProperties myProperties) {
		super(myProperties);
		setNumPoints();
		
	}
	
	public void setNumPoints(){
		numPoints=getGoalProperties().getTotalPoints();
	}	
	
	public int getNumPoints(){
		return this.numPoints;
	}
	
}
