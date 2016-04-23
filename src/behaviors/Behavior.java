package behaviors;

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
	
	public BooleanProperty enabled;
	
	public Behavior(){
		enabled = new SimpleBooleanProperty(false);
	}
	public abstract void apply(Sprite spriteProperties, SpriteFactory spriteFactory);
	
	public boolean isEnabled(){
		return enabled.getValue();
	}
	public void enable(){
		enabled.set(true);
	}
	public void disable(){
		enabled.set(false);
	}
}