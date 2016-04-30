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
	}
	@Override
	public Collision clone() {
		return new PointsCollision(getValue());
	}
}
