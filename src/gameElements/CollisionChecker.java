package gameElements;

import java.awt.Rectangle;

public class CollisionChecker {
	public CollisionChecker(){
		
	}
	
	public boolean areColliding(Sprite spriteOne, Sprite spriteTwo){
		// check if either are null
		Rectangle rectangleSpriteOne = spriteToRectangle(spriteOne);
		Rectangle rectangleSpriteTwo = spriteToRectangle(spriteTwo);
		return rectangleSpriteOne.intersects(rectangleSpriteTwo);
	}
	
	private Rectangle spriteToRectangle(Sprite sprite){
		Rectangle rectangle = new Rectangle ( (int) sprite.getX().doubleValue(), (int) sprite.getY().doubleValue(), (int) sprite.getWidth().doubleValue(), (int) sprite.getHeight().doubleValue());
		return rectangle;
	}
	
}
