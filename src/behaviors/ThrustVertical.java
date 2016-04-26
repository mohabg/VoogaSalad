package behaviors;

import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * A subclass of Movement that is used by the game authoring environment to move the sprite vertically by some number of units.
 * Has the moveY attribute to determine how much the sprite should move
 */

public class ThrustVertical extends Movement{

	public ThrustVertical() {
		super();
	}

	public ThrustVertical(double value) {
		super(value);
	}

	/**
	 * @param sprite Takes the sprite as an argument, gets the current Y position and adds to it the DoubleProperty value that the user
	 * specified(i.e. it moves the sprite vertically some amount specified by moveY)
	 */

	@Override
	public void move(IActions actions) {
		SpriteProperties properties = actions.getSpriteProperties();
		double currentXVelocity = properties.getMyXvel().doubleValue();
		double currentYVelocity = properties.getMyYvel().doubleValue();
		double xOrientation = Math.sin(Math.toRadians(-1 * properties.getMyAngle()));
		double yOrientation = Math.cos(Math.toRadians(-1 * properties.getMyAngle()));
		properties.setMyXvel(currentXVelocity + xOrientation * getValue());
		properties.setMyYvel(currentYVelocity + yOrientation * getValue());

	}


}