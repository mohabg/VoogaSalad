package collisions;

import gameElements.Sprite;

public class PowerUpCollision {
	
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
