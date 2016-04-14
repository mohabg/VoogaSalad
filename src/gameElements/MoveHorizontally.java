package gameElements;

public class MoveHorizontally extends Movement{
	
	private double moveX;
	
	public void setMoveX(double moveX){
		this.moveX=moveX;
	}
	
	public MoveHorizontally() {
		super();
	}

	@Override
	public void move(Sprite sprite) {
		sprite.setX(toDoubleProperty(sprite.getX().add(moveX)));
		
	}

}
