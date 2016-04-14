package collisions;

import java.awt.Rectangle;

import gameElements.Sprite;

public class CollisionChecker {
	public CollisionChecker(){
		
	}
	
	/**
	 * @param sprite1 Determines whether spriteOne or spriteTwo are intersecting
	 */
	public boolean areColliding(Sprite spriteOne, Sprite spriteTwo){
		// check if either are null
		Rectangle rectangleSpriteOne = spriteToRectangle(spriteOne);
		Rectangle rectangleSpriteTwo = spriteToRectangle(spriteTwo);
		return rectangleSpriteOne.intersects(rectangleSpriteTwo);
	}
	
	/**
	 * @param sprite Turns Sprite into Rectangle(in order to enable the areColliding method)
	 * @see areColliding
	 */
	
	private Rectangle spriteToRectangle(Sprite sprite){
		Rectangle rectangle = new Rectangle ( (int) sprite.getX().doubleValue(), (int) sprite.getY().doubleValue(), (int) sprite.getWidth().doubleValue(), (int) sprite.getHeight().doubleValue());
		return rectangle;
	}
	
}
