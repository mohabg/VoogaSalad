package behaviors;

import gameElements.ISpriteProperties;
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
	 * @param actions Takes the actions as an argument, gets the current Y position and adds to it the DoubleProperty value that the user
	 * specified(i.e. it moves the sprite vertically some amount specified by moveY)
	 */

	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		double currentXVelocity = properties.getXVel();
		double currentYVelocity = properties.getYVel();
		double xOrientation = Math.sin(Math.toRadians(-1 * properties.getAngle()));
		double yOrientation = Math.cos(Math.toRadians(-1 * properties.getAngle()));
		properties.setXVel(currentXVelocity + xOrientation * getValue());
		properties.setYVel(currentYVelocity + yOrientation * getValue());

	}

	@Override
	public Behavior getClone() {
		return new ThrustVertical(this.getValue());
	}


}