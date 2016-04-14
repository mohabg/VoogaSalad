package behaviors;

import gameElements.Sprite;

public class MoveVertically extends Movement{

	public MoveVertically(double value) {
		super(value);
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setY(toDoubleProperty(sprite.getY().add(getValue())));
		
	}

	
	
}