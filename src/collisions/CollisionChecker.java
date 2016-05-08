package collisions;

import java.awt.Rectangle;

import events.Trigger;
import gameElements.ISprite;
import gameElements.Sprite;

public class CollisionChecker implements Trigger {
	
	private boolean isTriggered;
	
	public CollisionChecker(){
		isTriggered = false;
	}
	/**
	 * @param sprite1 Determines whether spriteOne or spriteTwo are intersecting
	 */
	public void checkColliding(ISprite spriteOne, ISprite spriteTwo){
		// check if either are null
		Rectangle rectangleSpriteOne = spriteToRectangle(spriteOne);
		Rectangle rectangleSpriteTwo = spriteToRectangle(spriteTwo);
		isTriggered = rectangleSpriteOne.intersects(rectangleSpriteTwo);
	}
	
	/**
	 * @param sprite Turns Sprite into Rectangle(in order to enable the areColliding method)
	 * @see areColliding
	 */
	
	private Rectangle spriteToRectangle(ISprite sprite){
        return new Rectangle ( (int) sprite.getSpriteProperties().getX(), (int) sprite.getSpriteProperties().getY(), 
        						(int) sprite.getSpriteProperties().getWidth(), (int) sprite.getSpriteProperties().getHeight());
	}

	@Override
	public boolean isTriggered() {
		return isTriggered;
	}
	
}
