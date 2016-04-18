package behaviors;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;

/**
 * A sprite that acts as a shield to an actor--is usually used in the defense attributes of sprites 
 */

public class Shield extends Defense{
	
	private DoubleProperty rechargeTime;

	public Shield(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, Health myHealth2,
			ApplyBehaviorConditions behaviorConditions, double rechargeTime) {
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef, behaviorConditions);

		this.rechargeTime.set(rechargeTime);
	}

	public DoubleProperty getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(DoubleProperty rechargeTime) {
		this.rechargeTime=rechargeTime;
	}
	public void decrementRechargeTime(DoubleProperty decrement){
		rechargeTime.subtract(decrement);
	}

	
	/**
	 * @param sprite The method takes a sprite, which determines whether the isUsercontrolled is true
	 * @see isUserControlled
	 */
	@Override
	public boolean readyToDefend(Sprite sprite) {
		if(sprite.isUserControlled()){
			//Should depend on key input instead of this method
			return true;
		}
		else{
			//AI controlled shielding?
			ApplyBehaviorConditions behaviorConditions = getBehaviorConditions();
			
		}
		return false;
	}
}
