package collisions;

import behaviors.IActions;
import gameElements.Sprite;
import level.LevelProperties;

/**
 * When an Actor collides
 */


public class ActorCollision extends Collision{
	
	public ActorCollision() {
		super();
	}

	@Override
	public Collision clone() {
		return new ActorCollision();
	}
	
}
