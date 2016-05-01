package behaviors;

import gameElements.ISpriteProperties;

/**
 * Moves a pre-defined sprite horizontally by an amount defined by the instance variable moveX
 */

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
     // sprite.getSpriteProperties().setMyXvel(getValue());

	}

}
