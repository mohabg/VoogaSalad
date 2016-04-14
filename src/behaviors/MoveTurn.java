package behaviors;

import gameElements.Sprite;

public class MoveTurn extends Movement {

		public MoveTurn(double value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

		@Override
		public void move(Sprite sprite) {
            sprite.getSpriteProperties().setMyAngle((sprite.getAngle().doubleValue() + getValue()));
		}

	}

	
	
	

