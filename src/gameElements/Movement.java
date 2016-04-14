package gameElements;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

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
			if(sprite.canMove()){
				move(sprite);
			}
		}
		
		public void move(Sprite sprite) {
			//set x to zero when up/down is pressed
			
		}

		public DoubleProperty toDoubleProperty(DoubleBinding value){
			DoubleProperty doubleValue=new SimpleDoubleProperty(value.doubleValue());
			return doubleValue;
		}
}