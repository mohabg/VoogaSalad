package gameElements;

public abstract class Movement implements Behavior{
		
		private Sprite mySprite;
		private int incrementByX;
		private int incrementByY;
		
		
		public Movement(Sprite sprite, int incrementByX, int incrementByY){
			this.incrementByX=incrementByX;
			this.incrementByY=incrementByY;
			this.mySprite = sprite;
		}
		
		public int getIncrementByX() {
			return incrementByX;
		}

		public int getIncrementByY() {
			return incrementByY;
		}

		public Sprite getSpriteProperties(){
			return mySprite;
		}
}	
