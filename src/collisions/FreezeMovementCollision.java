package collisions;

import behaviors.IActions;
import gameElements.Sprite;
import level.LevelProperties;

/**
 * Stops the movement of a sprite on collision
 */


public class FreezeMovementCollision extends Collision{

	public FreezeMovementCollision() {
		super();
	}

	/**
	 * @param other The Sprite that you want to stop from moving
	 */	
	public void handleCollision(EnemyCollision other, LevelProperties levelProperties) {
		levelProperties.getSpriteForCollision(other).getSpriteProperties().disableMovement();
	}

	@Override
	public Collision clone() {
		return new FreezeMovementCollision();
	}
	public void execute(IActions action, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}
