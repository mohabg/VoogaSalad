package collisions;

import gameElements.Sprite;
import level.LevelProperties;

/**
 * Stops the movement of a sprite on collision
 */


public class FreezeMovementCollision extends Collision{

	public FreezeMovementCollision(Sprite sprite, double value) {
		super(sprite, value);
	}

	/**
	 * @param other The Sprite that you want to stop from moving
	 */	
	public void handleCollision(EnemyCollision other, LevelProperties levelProperties) {
		other.getSprite().disableMovement();
	}
}