package collisions;

import java.awt.Rectangle;

import events.Trigger;
import gameElements.Sprite;

public class CollisionChecker implements Trigger {
	
	private boolean isTriggered;
	private triggerType type;
	
	public CollisionChecker(){
		isTriggered = false;
		type = triggerType.COLLISION;
	}
	
	/**
	 * @param sprite1 Determines whether spriteOne or spriteTwo are intersecting
	 */
	public boolean areColliding(Sprite spriteOne, Sprite spriteTwo){
		// check if either are null
		Rectangle rectangleSpriteOne = spriteToRectangle(spriteOne);
		Rectangle rectangleSpriteTwo = spriteToRectangle(spriteTwo);
		isTriggered = rectangleSpriteOne.intersects(rectangleSpriteTwo);
		return isTriggered;
	}
	
	/**
	 * @param sprite Turns Sprite into Rectangle(in order to enable the areColliding method)
	 * @see areColliding
	 */
	
	private Rectangle spriteToRectangle(Sprite sprite){
		Rectangle rectangle = new Rectangle ( (int) sprite.getX().doubleValue(), (int) sprite.getY().doubleValue(), (int) sprite.getWidth().doubleValue(), (int) sprite.getHeight().doubleValue());
		return rectangle;
	}

	@Override
	public boolean isTriggered() {
		return isTriggered;
	}
	
}
