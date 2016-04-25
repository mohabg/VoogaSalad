package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import gameplayer.SpriteFactory;

/**
 * A subclass of Defense, this describes armor that could go over a sprite.
 */


public class Armor extends Defense{

	public Armor(){
		super();
	}
	/**
	 * @param sprite Determines whether the sprite is eligible to use its defense
	 */

	@Override
	public void defend(Sprite sprite, SpriteFactory spriteFactory) {
	
		
	}
	
	

}
