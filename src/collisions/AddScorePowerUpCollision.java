package collisions;

import gameElements.ISprite;
import level.LevelProperties;

public class AddScorePowerUpCollision extends Collision {

	public AddScorePowerUpCollision(){
		this(0);
	}
	public AddScorePowerUpCollision(double value) {
		super(value);
	}

	/**
	 * @param other Checks the behavior of the other sprite that this collided with, and implements that corresponding behavior
	 */
	public void handleCollision(ActorCollision other, LevelProperties levelProperties){
		levelProperties.addScore((int) getValue());
	}

	@Override
	public Collision clone() {
		return new AddScorePowerUpCollision(getValue());
	}

}