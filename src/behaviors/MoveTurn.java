package behaviors;

import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;

// This entire file is part of my masterpiece.
// Jesse Rencurrell Pollack
public class MoveTurn extends Movement {
	
	public MoveTurn(){
		super();
	}
	
	public MoveTurn(double value){
		super(value);
	}

	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		properties.setAngle(properties.getAngle() + getValue());
	}

	@Override
	public Behavior getClone() {
		return new MoveTurn(this.getValue());
	}

}
