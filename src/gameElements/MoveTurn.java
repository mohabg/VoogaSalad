package gameElements;

import javafx.beans.property.DoubleProperty;

/**
 * A subclass of Movement that is used by the game authoring environment to move the angle of the sprite by some number of units. 
 * Has the moveY attribute to determine how much the sprite should move 
 */
public class MoveTurn extends Movement {
	
	private DoubleProperty degree;
	
		public void setDegree(DoubleProperty degree) {
		this.degree = degree;
	}

		public MoveTurn(Actor sprite) {
			super();
		}
		
		/**
		 * @param sprite Gets the sprite and turns the sprite a certain number of degrees(turns the sprite)
		 */
		
		@Override
		public void move(Sprite sprite) {
			sprite.setAngle(toDoubleProperty(sprite.getAngle().add(getValue())));	
		}

	}

	
	
	

