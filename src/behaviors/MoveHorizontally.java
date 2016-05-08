package behaviors;

import gameElements.ISpriteProperties;

// This entire file is part of my masterpiece.
// Jesse Rencurrell Pollack

public class MoveHorizontally extends Movement{
	public MoveHorizontally() {
		super();
	}
	public MoveHorizontally(double value) {
		super(value);
	}

	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		properties.setX(properties.getX() + getValue());
	}
	@Override
	public Behavior getClone() {
		return new MoveHorizontally(getValue());
	}

}
