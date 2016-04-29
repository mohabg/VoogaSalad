package collisions;

import behaviors.IActions;
import gameElements.ISprite;
import gameElements.Sprite;
import level.LevelProperties;

/**
 * A collision that causes one or both of the colliders to gain 
 * some power up, such as more health, more weapons, more lives, a bonus level, etc 
 */

public class AddLifePowerUpCollision extends Collision{
	
	public AddLifePowerUpCollision(){
		this(0);
	}
	public AddLifePowerUpCollision(double value) {
		super(value);
	}

	/**
	 * @param other Checks the behavior of the other sprite that this collided with, and implements that corresponding behavior
	 */
	public void handleCollision(ActorCollision other, LevelProperties levelProperties){
		ISprite collidingSprite = levelProperties.getSpriteForCollision(other);
		collidingSprite.getMyHealth().addLife();
	}
	@Override
	public Collision clone() {
		return new AddLifePowerUpCollision(getValue());
	}
	public void execute(IActions action, LevelProperties levProps) {
		// TODO Auto-generated method stub
	}
	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
	
}
