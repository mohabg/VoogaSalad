package behaviors;

import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MoveVertically extends Movement{

	public MoveVertically() {
		super();
	}

	public MoveVertically(double value) {
		super(value);
	}

	/**
	 * @param sprite Takes the sprite as an argument, gets the current Y position and adds to it the DoubleProperty value that the user
	 * specified(i.e. it moves the sprite vertically some amount specified by moveY)
	 */

	@Override
	public void move(IActions actions) {
		SpriteProperties properties = actions.getSpriteProperties();
		properties.setMyY(properties.getMyY().doubleValue() + getValue());

	    //sprite.getSpriteProperties().setMyYvel(getValue());
	}


}