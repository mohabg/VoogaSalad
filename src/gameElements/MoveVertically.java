package gameElements;

public class MoveVertically extends Movement{

	private double moveY;
	
	public void getMoveY(double moveY){
		this.moveY=moveY;
	}
	
	public MoveVertically(Actor sprite) {
		super(sprite);
	}

	@Override
	public void apply(Sprite sprite) {
		sprite.setY(sprite.getY()+moveY);
		
	}

	
	
}