package behaviors;

import gameElements.Sprite;

import javafx.beans.property.DoubleProperty;

/**
 * A subclass of Movement that is used by the game authoring environment to move the angle of the sprite by some number of units. 
 * Has the moveY attribute to determine how much the sprite should move 
 */
public class MoveTurn extends Movement {
	
	public MoveTurn(double value){
		super(value);
	}
		
		/**
		 * @param sprite Gets the sprite and turns the sprite a certain number of degrees(turns the sprite)
		 */
		
		@Override
		public void move(Sprite sprite) {
            sprite.getSpriteProperties().setMyAngle((sprite.getAngle().doubleValue() + getValue()));
		}

	}

	
	
	

