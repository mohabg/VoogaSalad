package goals;

import gameElements.Sprite;
import level.Level;

/**
 * This class has methods to determine if a goal has been met for a specific level .The level instance variable is taken in the visit 
 *  method and compared to the amount of points one should get in this level to pass. 
 */


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
	
	public boolean visit(StayAliveGoal goal){
		boolean enemyBoolean=true;
		for(Sprite sprite: level.getSpriteMap().values()){
			if(!sprite.isUserControlled()){
				enemyBoolean=false;
				break;
			}
		}
		return enemyBoolean;
	}
	
	public boolean visit(KillBossGoal goal){
		boolean bossBoolean=true;
		for(Integer integer: goal.getBossIDList()){
			if(getLevel().getSpriteMap().keySet().contains(integer)){
				bossBoolean=false;
				break;
			}
		}
		return bossBoolean;
		
	}
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
