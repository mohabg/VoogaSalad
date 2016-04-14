package gameElements;

/**
 * Describes the amount of points you need to get to the next level. The information is recieved from the 
 * getTotalPoints() method of the goalProperties. 
 * @see getTotalPoints()
 */

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
