package collisions;

import gameElements.Sprite;
import level.LevelProperties;

/**
 * A collision that gives points to the User
 */

public class PointsCollision extends Collision{

	public PointsCollision(Sprite sprite){
		super(sprite);
	}
	public PointsCollision(Sprite sprite, double value){
		super(sprite, value);
	}
	protected void handleCollision(EnemyCollision enemy, LevelProperties levelProperties){
		//Add points to score
		//Need to get score from CollisionHandler and store in Collision
	}
}
