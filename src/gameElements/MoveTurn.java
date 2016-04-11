package gameElements;

public class MoveTurn extends Movement {
	
	private double degree;
	
		public void setDegree(double degree) {
		this.degree = degree;
	}

		public MoveTurn(Actor sprite) {
			super(sprite);
		}
		
		@Override
		public void apply(Sprite sprite) {
			sprite.setAngle(this.degree+sprite.getAngle());	
		}

	}

	
	
	

