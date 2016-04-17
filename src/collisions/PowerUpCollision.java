package collisions;

import gameElements.Sprite;
import level.LevelProperties;

/**
 * A collision that causes one or both of the colliders to gain 
 * some power up, such as more health, more weapons, more lives, a bonus level, etc 
 */

public class PowerUpCollision extends Collision{
	
	public PowerUpCollision(Sprite sprite){
		super(sprite);
	}
	public PowerUpCollision(Sprite sprite, double value) {
		super(sprite, value);
	}

	/**
	 * @param other Checks the behavior of the other sprite that this collided with, and implements that corresponding behavior
	 */
	public void handleCollision(Collision other, LevelProperties levelProperties){
		Sprite collidingSprite = other.getSprite();
		if(collidingSprite.isUserControlled()){
			for(String className : collidingSprite.getBehaviors().keySet()){
				if(className.equals("Attack")){
					
					
				}
			}
		}
	}
	
}
