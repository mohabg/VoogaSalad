package gameElements;

public class MoveVertically extends Movement{

	private double moveY;
	
	public void getMoveY(double moveY){
		this.moveY=moveY;
	}
	
	public MoveVertically() {
		super();
		moveY = 1;
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setY(toDoubleProperty(sprite.getY().add(moveY)));
		
	}

	
	
}