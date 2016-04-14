package behaviors;

import gameElements.Sprite;
/**
 * 
 * This is the interface the Sprite uses to execute all of its actions. 
 *
 */
public interface Behavior {
	
	public void apply(Sprite spriteProperties);
	
}