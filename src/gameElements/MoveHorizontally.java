package gameElements;

public class MoveHorizontally extends Movement{
	
	private double moveX;
	
	public void setMoveX(double moveX){
		this.moveX=moveX;
	}
	
	public MoveHorizontally(Actor sprite) {
		super(sprite);
	}

	@Override
	public void apply(Sprite sprite) {
		sprite.setX(toDoubleProperty(sprite.getX().add(moveX)));
		
	}

	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}


}
