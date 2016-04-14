package gameElements;

public class MoveHorizontally extends Movement{
	

	public MoveHorizontally(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setX(toDoubleProperty(sprite.getX().add(getValue())));
		
	}

}
