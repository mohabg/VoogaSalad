package gameElements;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import javafx.beans.property.DoubleProperty;

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
