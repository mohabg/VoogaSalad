package collisions;

import behaviors.IActions;
import gameElements.Sprite;
import level.LevelProperties;

/**
 * When colliding with a non-user controlled sprite
 */

public class EnemyCollision extends Collision{


	public EnemyCollision() {
		super();
	}

	@Override
	public Collision clone() {
		return new EnemyCollision();
	}
}
