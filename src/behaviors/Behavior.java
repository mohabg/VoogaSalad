package behaviors;

import gameElements.Sprite;
import gameplayer.SpriteFactory;
/**
 * 
 * This is the interface the Sprite uses to execute all of its actions. 
 *
 */
public interface Behavior {
	
	public void apply(Sprite spriteProperties, SpriteFactory spriteFactory);
	
}