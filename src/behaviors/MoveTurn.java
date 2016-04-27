package behaviors;

import gameElements.Sprite;
import gameElements.SpriteProperties;
import javafx.beans.property.DoubleProperty;

/**
 * A subclass of Movement that is used by the game authoring environment to move
 * the angle of the sprite by some number of units. Has the moveY attribute to
 * determine how much the sprite should move
 */
public class MoveTurn extends Movement {
	
	public MoveTurn(double value){
		super(value);
	}

	@Override
	public void move(IActions actions) {
		SpriteProperties properties = actions.getSpriteProperties();
		properties.setMyAngle(properties.getMyAngle() + getValue());
		
	}

}
