package behaviors;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Deals with an array of behaviors having to do with how Sprites defend themselves. The enabled boolean lets one know if the 
 * sprite currently has this defense enabled. Behavior conditions refers to an array of properties that help determine if the 
 * Sprite is eligible for defending itself. It is only used for enemy sprites.
 * @see ApplyBehaviorConditions
 */


public abstract class Defense extends Sprite implements Behavior {

	private BooleanProperty enabled;
	private ApplyBehaviorConditions behaviorConditions;

	public Defense(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef,  ApplyBehaviorConditions behaviorConditions) {
		
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef);
		this.behaviorConditions = behaviorConditions;
		this.enabled = new SimpleBooleanProperty();
	}
	
	public boolean isEnabled(){
		return enabled.get();
	}
	public void enableDefense(){
		enabled.set(true);
		//enable image
	}
	public void disableDefense(){
		enabled.set(false);
		//disable image
	}
	public void takeDamage(double damage){
		this.getHealth().decrementHealth(damage);
	}
	public ApplyBehaviorConditions getBehaviorConditions(){
		return behaviorConditions;
	}
	
	/**
	 * @param sprite Enables the defense of a sprite, provided the readyToDefend boolean is on
	 * @see readyToDefend
	 */
	@Override
	public void apply(Sprite sprite){
		if(readyToDefend(sprite)){
			enableDefense();
			this.setCoord(sprite.getX(), sprite.getY());
		}
		else{
			disableDefense();
		}
	}
	public abstract boolean readyToDefend(Sprite sprite);

}