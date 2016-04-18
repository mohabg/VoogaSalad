package behaviors;

import gameElements.Sprite;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

	/**
	 * Superclass for Movements--takes the sprite that wants to be used as an instance variable, and a subclass of movement is called
	 * to move the sprite 
	 */

public abstract class Movement implements Behavior{
	//New movement with list of movements
	//run each movement a specified number of times before going to next

	private Sprite mySprite;
	private DoubleProperty value;
	
	public Movement(){
		this(0);
	}
	public Movement(double value){
		this.value = new SimpleDoubleProperty(value);
	}
	
	public Sprite getSpriteProperties(){
		return mySprite;
	}
	public double getValue(){
		return value.doubleValue();
	}
	/**
	 * @param sprite This method checks conditions for movement, and moves the sprite correspondingly
	 */
	@Override
	public void apply(Sprite sprite) {
		if(sprite.canMove()){
			move(sprite);
		}
	}
	
	public abstract void move(Sprite sprite);
}