package collisions;

import gameElements.Sprite;
import level.LevelProperties;

public class ExplosionCollision extends Collision{
	/**
	 * Causes explosion on collision
	 */
	
	public ExplosionCollision() {
		this(0);
	}
	
	public ExplosionCollision(double value) {
		super(value);
	}

	public void handleCollision(EnemyCollision other, LevelProperties levelProperties){
		//Explode
	}
}
