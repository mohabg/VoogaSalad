package collisions;

import behaviors.IActions;
import gameElements.ISprite;
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
		ISprite thisSprite = levelProperties.getSpriteForCollision(this);
		ISprite collidingSprite = levelProperties.getSpriteForCollision(other);
		
		if(thisSprite.isUserControlled() != collidingSprite.isUserControlled()){
			thisSprite.kill();
		}
	}
	@Override
	public Collision clone() {
		return new DissapearCollision(this.getValue());
	}
	public void execute(IActions action, LevelProperties levProps) {

	}
	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}
