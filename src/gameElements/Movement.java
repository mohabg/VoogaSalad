package gameElements;

public abstract class Movement implements Behavior{
		
		private Sprite spriteProperties;
		
		public Movement(MobileBehavior sprite){
			this.spriteProperties = sprite.getProperties();
		}
		
		public Sprite getSpriteProperties(){
			return spriteProperties;
		}
}	
