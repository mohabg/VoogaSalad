package gameElements;

/**
 * A collision that causes one or both of the colliders to gain 
 * some power up, such as more health, more weapons, more lives, a bonus level, etc 
 */

public class PowerUpCollision {
	
	
	/**
	 * @param other Checks the behavior of the other sprite that this collided with, and implements that corresponding behavior
	 */
	protected void handleCollision(Collision other){
		Sprite collidingSprite = other.getSprite();
		if(collidingSprite.isUserControlled()){
			for(String className : collidingSprite.getBehaviors().keySet()){
				if(className.equals("Attack")){
					
					
				}
			}
		}
	}
	
}
