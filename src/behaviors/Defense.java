package behaviors;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

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