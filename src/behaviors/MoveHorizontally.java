package behaviors;

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
	SpriteProperties properties = actions.getSpriteProperties();
	properties.setMyX(properties.getMyX().doubleValue() + getValue());
     // sprite.getSpriteProperties().setMyXvel(getValue());

	}

}
