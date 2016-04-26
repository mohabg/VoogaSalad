package behaviors;

import gameElements.ApplyBehaviorConditions;
import gameElements.Sprite;
import gameplayer.SpriteFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * 
 * This is the interface the Sprite uses to execute all of its actions.
 *
 */
public abstract class Behavior {

	private ApplyBehaviorConditions behaviorConditions;
	
	public Behavior(){
		behaviorConditions = new ApplyBehaviorConditions();
	}
	public abstract void apply(Sprite spriteProperties, SpriteFactory spriteFactory);
	
	public boolean isReady(Sprite sprite){
		return this.behaviorConditions.ready(sprite);
	}
	public boolean isEnabled(){
		return this.behaviorConditions.isEnabled();
	}
	public void enable(){
		this.behaviorConditions.enable();
	}
	public void disable(){
		this.behaviorConditions.disable();
	}
	public ApplyBehaviorConditions getBehaviorConditions() {
		return behaviorConditions;
	}
	public void setBehaviorConditions(ApplyBehaviorConditions behaviorConditions) {
		this.behaviorConditions = behaviorConditions;
	}
}