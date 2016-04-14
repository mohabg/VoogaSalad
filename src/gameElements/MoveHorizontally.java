package gameElements;

public class MoveHorizontally extends Movement{
	
<<<<<<< HEAD

=======
>>>>>>> 65398fd930c8113a233faf86becbc8e53c08fa85
	public MoveHorizontally(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setX(toDoubleProperty(sprite.getX().add(getValue())));
		
	}

}
