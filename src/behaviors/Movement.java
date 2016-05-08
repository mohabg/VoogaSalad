package behaviors;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.ILevelProperties;
import level.LevelProperties;
/**
 * THIS ENTIRE FILE IS PART OF MY MASTERPIECE
 * 
 * This class is provided as an example of the type of class that would extend from Behavior. It is made abstract in order to reduce 
 * duplicated code amongst its subclasses, since different movements have a lot in common. It is worthy to note that the method that 
 * must now be implemented is no longer "apply", but "move" instead.
 * @author mohabgabal
 *
 */

public abstract class Movement extends Behavior{

	private DoubleProperty value;
	
	public Movement(){
		this(0);
	}
	public Movement(double value){
		super();
		this.value = new SimpleDoubleProperty(value);
	}
	
	public double getValue(){
		return value.doubleValue();
	}
	
	@Override
	public void execute(IActions actions, ILevelProperties levProps){
		this.enable();
		apply(actions);
	}
	@Override 
	public void stop(IActions actions, ILevelProperties levProps){
		if(actions.getSprite().equals(levProps.getSpriteMap().getUserControlledSprite())){
			this.disable();
		}
	}
	/**
	 * @param actions This method checks conditions for movement, and moves the sprite correspondingly
	 */
	@Override
	public void apply(IActions actions) {
		if(actions.spriteCanMove() && this.isEnabled()){
			move(actions);
		}
	}
	
	public abstract void move(IActions actions);
}