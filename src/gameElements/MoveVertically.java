package gameElements;

import javafx.beans.property.DoubleProperty;
/**
 * A subclass of Movement that is used by the game authoring environment to move the sprite vertically by some number of units. 
 * Has the moveY attribute to determine how much the sprite should move 
 */

public class MoveVertically extends Movement{

	private DoubleProperty moveY;
	
	public void getMoveY(DoubleProperty moveY){
		this.moveY=moveY;
	}
	
	public MoveVertically(Actor sprite) {
		super(sprite);
	}

	/**
	 * @param sprite Takes the sprite as an argument, gets the current Y position and adds to it the DoubleProperty value that the user
	 * specified(i.e. it moves the sprite vertically some amount specified by moveY)
	 */
	
	@Override
	public void move(Sprite sprite) {
		sprite.setY(toDoubleProperty(sprite.getY().add(moveY)));
		
	}

	
	
}