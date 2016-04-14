package gameElements;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;

/**
 * A subclass of Defense, this describes armor that could go over a sprite.
 */


public class Armor extends Defense{

	public Armor(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, Health myHealth2,
			ApplyBehaviorConditions behaviorConditions) {
		
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef, behaviorConditions);
	}

	/**
	 * @param sprite Determines whether the sprite is eligible to use its defense
	 */
	@Override
	public boolean readyToDefend(Sprite sprite) {
		for(Behavior behavior : sprite.getBehaviors().values()){
			if(behavior instanceof Defense){
				if( ((Defense) behavior).isEnabled()){
					return false;
				}
			}
		}
		return true;
	}

}
