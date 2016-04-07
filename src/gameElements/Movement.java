package gameElements;

public abstract class Movement implements Behavior{
		
		private SpriteProperties spriteProperties;
		
		public Movement(Sprite sprite){
			this.spriteProperties = sprite.getProperties();
		}
		
		public SpriteProperties getSpriteProperties(){
			return spriteProperties;
		}
}	
