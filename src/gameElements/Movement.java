package gameElements;

public abstract class Movement implements Behavior{
		
		private Sprite mySprite;
		
		public Movement(Sprite sprite){
			this.mySprite = sprite;
		}
		
		public Sprite getSpriteProperties(){
			return mySprite;
		}
}	
