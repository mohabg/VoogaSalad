package gameElements;

/**
 * Upon collision the sprite in question disappears from the level
 */


public class DissapearCollision extends Collision{
	
	public DissapearCollision(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleCollision(Collision other){
		Sprite mySprite = getSprite();
		//Set Alpha to zero
	}
}
