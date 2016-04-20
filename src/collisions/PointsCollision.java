package collisions;

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
	protected void handleCollision(EnemyCollision enemy, LevelProperties levelProperties){
		//Add points to score
		//Need to get score from CollisionHandler and store in Collision
	}
}
