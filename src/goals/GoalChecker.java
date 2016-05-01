package goals;

import gameElements.ISprite;
import javafx.beans.property.IntegerProperty;
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
	
	public GoalChecker(){
		level= new Level();
	}
	

	@Override
	public boolean visit(PointsGoal goal) {
//		System.out.println("reached pointsgoalchecker");
//	    System.out.println("goalpoints"+goal.getNumPoints());
//	    System.out.println("levelpoints"+getLevel().getCurrentPoints());
//      System.out.println(getLevel().getCurrentPoints()); 
//		System.out.println("goalboolean");
//      System.out.println(goal.getNumPoints() < getLevel().getCurrentPoints());
		return goal.getNumPoints().intValue() < getLevel().getCurrentPoints();
	}
	
	public boolean visit(StayAliveGoal goal){
		//System.out.println("reached visit() method in stay alive checker");

	System.out.println("spritesize"+level.getSpriteMap().getSprites().size());
		boolean enemyBoolean=true;
		for(ISprite sprite: level.getSpriteMap().getSprites()){
			if(!sprite.isUserControlled()){
				enemyBoolean=false;
				break;
			}
		}
		System.out.println("enemyboolean" + enemyBoolean);
		return enemyBoolean;
	}
	
	public boolean visit(KillBossGoal goal){
		System.out.println("reached kilchecker");
		boolean bossBoolean=true;
		for(IntegerProperty integerProperty: goal.getBossIDList().get()){
			Integer integer=integerProperty.intValue();
			if(getLevel().getSpriteMap().getSpriteIDList().contains(integer)){
				bossBoolean=false;
				break;
			}
		}
		return bossBoolean;
	}
	

	@Override
	public boolean visit(Goal goal) {
		System.out.println("reached blank checker");

		// TODO Auto-generated method stub
		return false;
	}
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
