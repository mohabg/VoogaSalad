package collisions;

public class FreezeMovementCollision extends Collision{

	public FreezeMovementCollision(double value) {
		super(value);
	}

	protected void handleCollision(EnemyCollision other) {
		other.getSprite().disableMovement();
	}
}
