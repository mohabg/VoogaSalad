package behaviors;

import gameElements.Sprite;

import javafx.beans.property.DoubleProperty;

/**
 * Moves a pre-defined sprite horizontally by an amount defined by the instance variable moveX
 */

public class MoveHorizontally extends Movement{
	public MoveHorizontally(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Sprite sprite) {
     // sprite.getSpriteProperties().setMyX((sprite.getX().doubleValue() + getValue()));
      sprite.getSpriteProperties().setMyXvel(getValue());

	}

}
