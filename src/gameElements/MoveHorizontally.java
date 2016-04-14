package gameElements;

import javafx.beans.property.DoubleProperty;

/**
 * Moves a pre-defined sprite horizontally by an amount defined by the instance variable moveX
 */

public class MoveHorizontally extends Movement{
	
	private DoubleProperty moveX;
	
	public void setMoveX(DoubleProperty moveX){
		this.moveX=moveX;
	}
	

	
	@Override
	public void move(Sprite sprite) {
		sprite.setX(toDoubleProperty(sprite.getX().add(getValue())));
		
	}

}
