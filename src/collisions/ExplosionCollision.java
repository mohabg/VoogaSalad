package collisions;

import gameElements.Sprite;
import level.LevelProperties;

public class ExplosionCollision extends Collision{
	/**
	 * Causes explosion on collision
	 */

	public ExplosionCollision(Sprite sprite){
		super(sprite);
	}
	public ExplosionCollision(Sprite sprite, double value) {
		super(sprite, value);
		// TODO Auto-generated constructor stub
	}

	public void handleCollision(EnemyCollision other, LevelProperties levelProperties){
		//Explode
	}
}
