package gameElements;

import java.util.List;
import java.util.Map;
import java.util.Random;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import javafx.beans.property.IntegerProperty;

public abstract class Attack extends Sprite implements Behavior {
	
	private IntegerProperty ammunition;
	private IntegerProperty chargeTime;
	private ApplyBehaviorConditions behaviorConditions;
	private Movement movement;
	
	public Attack(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef);
		
	}
	public Attack(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, int ammunition, int chargeTime,
			ApplyBehaviorConditions behaviorConditions, Movement movement) {
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef);
		this.ammunition.set(ammunition);
		this.chargeTime.set(chargeTime);
		this.behaviorConditions = behaviorConditions;
		this.movement = movement;
	}



	public boolean readyToShoot(Sprite sprite) {
		if(sprite.isUserControlled()){
			return true;
		}
		else{
			//AI controlled
			double frameDelay = behaviorConditions.getFrameDelay();
			double framesPassed = behaviorConditions.getFramesPassed();
			double probability = behaviorConditions.getProbability();
			if (frameDelay > 0 && probability > 0) {
				if (framesPassed >= frameDelay) {
					if (Math.random() < probability) {
						return true;
						}
				}
			}
		}
		return false;
	}

	
	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	public int getChargeTime() {
		return chargeTime.get();
	}

	public void setChargeTime(int chargeTime) {
		this.chargeTime.set(chargeTime);;
	}

	public int getAmmunition() {
		return ammunition.get();
	}

	public void setAmmunition(int ammunition) {
		this.ammunition.set(ammunition);;
	}

	public boolean hasAmmunitionLeft() {
		return ammunition.get() > 0;
	}
}