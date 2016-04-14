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
		
		@Override
		public void move(Sprite sprite) {
			sprite.setAngle(toDoubleProperty(sprite.getAngle().add(degree)));	
		}


	}

	
	
	

