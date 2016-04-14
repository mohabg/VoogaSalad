package gameElements;

import javafx.beans.property.DoubleProperty;

public class MoveVertically extends Movement{

	private DoubleProperty moveY;
	
	public void getMoveY(DoubleProperty moveY){
		this.moveY=moveY;
	}
	
	public MoveVertically(Actor sprite) {
		super(sprite);
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setY(toDoubleProperty(sprite.getY().add(moveY)));
		
	}

	
	
}