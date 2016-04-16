package collisions;

import gameElements.Sprite;
import level.LevelProperties;

/**
 * Upon collision the sprite in question disappears from the screen
 */


public class DissapearCollision extends Collision{
	
	public DissapearCollision(Sprite sprite){
		super(sprite);
	}
	public DissapearCollision(Sprite sprite, double value) {
		super(sprite, value);
		// TODO Auto-generated constructor stub
	}


	public void handleCollision(Collision other, LevelProperties levelProperties){
		Sprite mySprite = getSprite();
		//Set Alpha to zero
		//Remove from sprite map
	}
}
