package gameElements;

/**
 * Stops the movement of a sprite on collision
 */


public class FreezeMovementCollision extends Collision{

	public FreezeMovementCollision(double value) {
		super(value);
	}

	/**
	 * @param other The Sprite that you want to stop from moving
	 */	
	protected void handleCollision(EnemyCollision other) {
		other.getSprite().stopMovement();
	}
}
