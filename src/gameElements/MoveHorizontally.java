package gameElements;

public class MoveHorizontally extends Movement{
	
	
	public MoveHorizontally(Actor sprite, int incrementByX, int incremenentByY) {
		super(sprite, incrementByX, incrementByX);
	}

	@Override
	public void apply(Sprite sprite) {
		sprite.setY(sprite.getY()+this.getIncrementByX());
		
	}


}
