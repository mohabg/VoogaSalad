package collisions;

import gameElements.Sprite;
import level.LevelProperties;

/**
 * Upon collision the sprite in question disappears from the screen
 */


public class DissapearCollision extends Collision{
	
	public DissapearCollision(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleCollision(Collision other, LevelProperties levelProperties){
		Sprite mySprite = getSprite();
		//Set Alpha to zero
		//Remove from sprite map
	}
}
