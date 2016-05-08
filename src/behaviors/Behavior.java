package behaviors;

import events.Executable;
import gameElements.Actions;
import gameElements.ExecuteConditions;
import gameElements.Sprite;
import gameplayer.SpriteFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import level.ILevelProperties;
import level.LevelProperties;

/**
 * THIS ENTIRE FILE IS PART OF MY MASTERPIECE
 * 
 * This class provides an abstraction of the different behaviors that a sprite can execute. This class provides two abstract methods for extension,
 * one of which simply returns another instance of this object, while the other actually executes the behavior. It contains an instance
 * of ExecuteConditions which is used for enemy AI to determine when they should take certain actions. By having all behaviors be executed 
 * through the apply method, updating the sprite boils down to simply going through all of its behaviors and calling apply() on it, if the
 * conditions are ready. Thus, it is easy to add new behaviors and the sprite class does not need to know anything at all about which behaviors
 * it has.
 * @author mohabgabal
 *
 */
public abstract class Behavior implements Executable{

	private ExecuteConditions behaviorConditions;
	
	public Behavior(){
		behaviorConditions = new ExecuteConditions();
	}
	@Override
	public void execute(IActions actions, ILevelProperties levProps){
		apply(actions);
	}
	public abstract void apply(IActions actions);
	
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
	public ExecuteConditions getBehaviorConditions() {
		return behaviorConditions;
	}
	public void setBehaviorConditions(ExecuteConditions behaviorConditions) {
		this.behaviorConditions = behaviorConditions;
	}
	public abstract Behavior getClone();
}