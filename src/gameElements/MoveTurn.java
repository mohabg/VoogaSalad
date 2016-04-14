package gameElements;

import javafx.beans.property.DoubleProperty;

public class MoveTurn extends Movement {
	
	private DoubleProperty degree;
	
		public void setDegree(DoubleProperty degree) {
		this.degree = degree;
	}

		public MoveTurn(Actor sprite) {
			super(sprite);
		}
		
		/**
		 * @param sprite Gets the sprite and turns the sprite a certain number of degrees(turns the sprite)
		 */
		
		@Override
		public void move(Sprite sprite) {
			sprite.setAngle(toDoubleProperty(sprite.getAngle().add(degree)));	
		}


	}

	
	
	

