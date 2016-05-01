package behaviors;

import gameElements.ISpriteProperties;

public class MoveVertically extends Movement{

	public MoveVertically() {
		super();
	}

	public MoveVertically(double value) {
		super(value);
		this.getBehaviorConditions().setProbability(1);
	}

	/**
	 * @param actions Takes the sprite as an argument, gets the current Y position and adds to it the DoubleProperty value that the user
	 * specified(i.e. it moves the sprite vertically some amount specified by moveY)
	 */

	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		properties.setY(properties.getY() + getValue());
	}


}