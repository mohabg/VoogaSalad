package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import gameplayer.SpriteFactory;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Describes attacking behavior of Sprites. Has the amount of ammunition(IntegerProperty ammunition), how long the 
 * bullets take to charge(IntegerProperty chargeTime), and ApplyBehavior conditions, which has attributes that help
 * determine whether an enemy sprite is eligible to shoot.
 * @see ApplyBehaviorConditions
 */


public abstract class Attack extends Sprite implements Behavior {
	
	private IntegerProperty ammunition;
	private IntegerProperty chargeTime;
	private ApplyBehaviorConditions behaviorConditions;
	private Movement movement;
	private SpriteProperties target;
	private SpriteFactory mySpriteFactory;
	
	public Attack() {
		this(new RefObject());
	}
	
	public Attack(RefObject myRef){
		this (new SpriteProperties(), new Health(), new ArrayList<Collision>(), new HashMap<String, Behavior>(), myRef);
	}
	
	public Attack(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef);
		ammunition = new SimpleIntegerProperty(1);
		chargeTime = new SimpleIntegerProperty(0);
		behaviorConditions = new ApplyBehaviorConditions(0.5, 0, 0, 0);
		movement = null;
		
	}
	public Attack(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, int ammunition, int chargeTime,
			ApplyBehaviorConditions behaviorConditions, Movement movement) {
		this(myProperties, myHealth, myCollisions, myBehaviors, myRef);		
		this.ammunition.set(ammunition);
		this.chargeTime.set(chargeTime);
		this.behaviorConditions = behaviorConditions;
		this.movement = movement;
	}

	/**
	 * @param sprite The sprite who's elibility to shoot you want to determine
	 */

	public boolean readyToShoot(Sprite sprite) {
		if(sprite.isUserControlled()){
			return true;
		}
		else{
			//AI controlled
			double frameDelay = behaviorConditions.getFrameDelay();
			int framesPassed = behaviorConditions.getFramesPassed();
			double probability = behaviorConditions.getProbability();
			if (frameDelay > 0 && probability > 0) {
				if (framesPassed >= frameDelay) {
					if (Math.random() < probability) {
						behaviorConditions.setFramesPassed(framesPassed + 1);
						return true;
						}
				}
			}
		}
		return false;
	}

	
	public SpriteFactory getMySpriteFactory() {
		return mySpriteFactory;
	}

	public void setSpriteFactory(SpriteFactory mySpriteFactory) {
		this.mySpriteFactory = mySpriteFactory;
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