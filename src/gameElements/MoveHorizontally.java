package gameElements;

import javafx.beans.property.DoubleProperty;

public class MoveHorizontally extends Movement{
	
	private DoubleProperty moveX;
	
	public void setMoveX(DoubleProperty moveX){
		this.moveX=moveX;
	}
	
	public MoveHorizontally(Actor sprite) {
		super(sprite);
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setX(toDoubleProperty(sprite.getX().add(moveX)));
		
	}

}
