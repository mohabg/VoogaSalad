package behaviors;

import events.Executable;
import gameElements.Actions;
import gameElements.ExecuteConditions;
import gameElements.Sprite;
import gameplayer.SpriteFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * 
 * This is the interface the Sprite uses to execute all of its actions.
 *
 */
public abstract class Behavior implements Executable{

	private ExecuteConditions behaviorConditions;
	
	public Behavior(){
		behaviorConditions = new ExecuteConditions();
	}
	@Override
	public void execute(IActions actions){
		apply(actions);
	}
	@Override
	public void stop(IActions actions){
		this.disable();
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
}