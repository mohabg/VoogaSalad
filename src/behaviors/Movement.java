package behaviors;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.LevelProperties;

	/**
	 * Superclass for Movements--takes the sprite that wants to be used as an instance variable, and a subclass of movement is called
	 * to move the sprite 
	 */

public abstract class Movement extends Behavior{

	private DoubleProperty value;
	
	public Movement(){
		this(0);
	}
	public Movement(double value){
		super();
		this.value = new SimpleDoubleProperty(value);
		this.value.addListener((o, ov, nv) -> {
			System.out.println("my new value is " + this.getValue());
		});
	}
	
	public double getValue(){
		return value.doubleValue();
	}
	
	@Override
	public void execute(IActions actions, LevelProperties levProps){
		this.enable();
		apply(actions, levProps);
	}
	@Override 
	public void stop(IActions actions, LevelProperties levProps){
		if(actions.getSprite().equals(levProps.getSpriteMap().getUserControlledSprite())){
			this.disable();
		}
	}
	/**
	 * @param actions This method checks conditions for movement, and moves the sprite correspondingly
	 */
	@Override
	public void apply(IActions actions, LevelProperties levProps) {
		if(actions.spriteCanMove() && this.isEnabled()){
			move(actions);
		}
	}
	
	public abstract void move(IActions actions);
}