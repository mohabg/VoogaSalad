package behaviors;

import gameElements.Sprite;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * A subclass of Movement that is used by the game authoring environment to move the sprite vertically by some number of units.
 * Has the moveY attribute to determine how much the sprite should move
 */

public class Thrust extends Movement{

	public Thrust() {
		super();
	}

	public Thrust(double value) {
		super(value);
	}

	/**
	 * @param sprite Takes the sprite as an argument, gets the current Y position and adds to it the DoubleProperty value that the user
	 * specified(i.e. it moves the sprite vertically some amount specified by moveY)
	 */

	@Override
	public void move(Sprite sprite) {
		DoubleProperty intensity=new SimpleDoubleProperty(getValue());
		sprite.thrustSprite(intensity);
		// sprite.getSpriteProperties().setMyY((sprite.getY().doubleValue() + getValue()));

	    //sprite.getSpriteProperties().setMyYvel(getValue());
	}


}