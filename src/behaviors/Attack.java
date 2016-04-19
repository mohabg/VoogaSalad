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


public abstract class Attack implements Behavior {
	
	private IntegerProperty ammunition;
	private IntegerProperty chargeTime;
	private ApplyBehaviorConditions behaviorConditions;
	private Movement movement;
	private RefObject myRef;
	private SpriteProperties target;
	
	public Attack() {
		this(new RefObject());
	}
	
	public Attack(RefObject myRef){
		this(myRef, 1 ,0);
	}

	public Attack(RefObject myRef, int ammunition, int chargeTime) {	
		this.ammunition = new SimpleIntegerProperty(ammunition);
		this.chargeTime = new SimpleIntegerProperty(chargeTime);
		this.behaviorConditions = new ApplyBehaviorConditions(0.5, 0, 0, 0);
		this.movement = movement;
		this.myRef = myRef;
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

	public RefObject getMyRef(){
		return myRef;
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