package collisions;

import behaviors.IActions;
import gameElements.Sprite;
import level.LevelProperties;

/**
 * A collision that gives points to the User
 */

public class PointsCollision extends Collision{

	public PointsCollision(){
		this(0);
	}
	public PointsCollision(double value){
		super(value);
	}
	public void handleCollision(EnemyCollision enemy, LevelProperties levelProperties){
		Integer newScore = (int) (levelProperties.getCurrentPoints() + getValue());
		levelProperties.setCurrentPoints(newScore);
		System.out.println("currentPoints"+newScore);
	}
	@Override
	public Collision clone() {
		return new PointsCollision(getValue());
	}
	public void execute(IActions action, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}
