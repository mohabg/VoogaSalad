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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A sprite that acts as a shield to an actor--is usually used in the defense attributes of sprites 
 */

public class Shield extends Defense{
	
	private DoubleProperty rechargeTime;
	
	public Shield(){
		this(new RefObject(), 0);
	}
	
	public Shield(RefObject myRef, double rechargeTime){
		super(myRef);
		this.rechargeTime = new SimpleDoubleProperty(rechargeTime);
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
