package behaviors;

import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;

/**
 * A subclass of Movement that is used by the game authoring environment to move
 * the angle of the sprite by some number of units. Has the moveY attribute to
 * determine how much the sprite should move
 */
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

}
