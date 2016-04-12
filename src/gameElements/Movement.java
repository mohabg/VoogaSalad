package gameElements;

public class Movement implements Behavior{
		//New movement with list of movements
		//run each movement a specified number of times before going to next
	
		private Sprite mySprite;
		
		public Movement(Sprite sprite){
			this.mySprite = sprite;
		}
		
		public Sprite getSpriteProperties(){
			return mySprite;
		}

		@Override
		public void apply(Sprite sprite) {
			// TODO Auto-generated method stub
			//set x to zero when up/down is pressed
		}

		@Override
		public boolean ready() {
			// TODO Auto-generated method stub
			return false;
		}
}