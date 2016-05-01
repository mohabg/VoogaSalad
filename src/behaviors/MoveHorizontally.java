package behaviors;

import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;

/**
 * Moves a pre-defined sprite horizontally by an amount defined by the instance variable moveX
 */

public class MoveHorizontally extends Movement{
	public MoveHorizontally() {
		super();
	}
	public MoveHorizontally(double value) {
		super(value);
		// TODO Auto-generated constructor stub
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
