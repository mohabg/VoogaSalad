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
	public void execute(IActions action, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}
