package collisions;

import gameElements.Sprite;

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
