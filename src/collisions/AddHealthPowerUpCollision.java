package collisions;

import gameElements.ISprite;
import level.ILevelProperties;

public class AddHealthPowerUpCollision extends Collision {

	public AddHealthPowerUpCollision(){
		this(0);
	}
	public AddHealthPowerUpCollision(double value) {
		super(value);
	}

	/**
	 * @param other Checks the behavior of the other sprite that this collided with, and implements that corresponding behavior
	 */
	public void handleCollision(ActorCollision other, ILevelProperties levelProperties){
		ISprite collidingSprite = levelProperties.getSpriteForCollision(other);
		collidingSprite.getMyHealth().incrementHealth(getValue());
	}
	
	@Override
	public Collision clone() {
		return new AddHealthPowerUpCollision(getValue());
	}
}