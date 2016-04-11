package gameElements;

public class MoveVertically extends Movement{

	
	public MoveVertically(Actor sprite, int incrementByX, int incremenentByY) {
		super(sprite, incrementByX, incrementByX);
	}

	@Override
	public void apply(Sprite sprite) {
		sprite.setY(sprite.getY()+this.getIncrementByX());
		
	}

}
