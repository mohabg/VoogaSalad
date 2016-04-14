package behaviors;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;

public class Armor extends Defense{

	public Armor(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, Health myHealth2,
			ApplyBehaviorConditions behaviorConditions) {
		
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef, behaviorConditions);
	}

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
