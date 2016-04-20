package collisions;

import gameElements.Sprite;
import level.LevelProperties;

/**
 * Upon collision the sprite in question disappears from the screen
 */


public class DissapearCollision extends Collision{
	
	public DissapearCollision(){
		this(0);
	}
	public DissapearCollision(double value) {
		super(value);
	}

	
	public void handleCollision(EnemyCollision other, LevelProperties levelProperties){
		killThisSprite(other, levelProperties);
	}
	
	public void handleCollision(ActorCollision other, LevelProperties levelProperties){
		killThisSprite(other,levelProperties);
	}
	
	private void killThisSprite(Collision other, LevelProperties levelProperties) {
		Sprite thisSprite = levelProperties.getSpriteForCollision(this);
		Sprite collidingSprite = levelProperties.getSpriteForCollision(other);
		
		if(thisSprite.isUserControlled() != collidingSprite.isUserControlled()){
			thisSprite.kill();
		}
	}
}
