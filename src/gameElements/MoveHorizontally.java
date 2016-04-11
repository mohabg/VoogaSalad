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
		sprite.setX(sprite.getX()+moveX);
		
	}


}
