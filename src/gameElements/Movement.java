package gameElements;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Movement implements Behavior{
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
		
		public abstract void move(Sprite sprite);

		public DoubleProperty toDoubleProperty(DoubleBinding value){
			DoubleProperty doubleValue=new SimpleDoubleProperty(value.doubleValue());
			return doubleValue;
		}
}