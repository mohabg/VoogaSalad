package gameElements;

public class Bullet extends Attack{
	
	private Sprite mySprite;
	private SpriteProperties myProperties;
	
	public Bullet(){
		myProperties = new SpriteProperties();
	}
	public Bullet(Sprite sprite){
		mySprite = sprite;
		myProperties = new SpriteProperties();
		myProperties.setCoord(mySprite.getProperties().getX(), mySprite.getProperties().getY());
	}
	@Override
	public void apply(Sprite sprite) {
		if(hasAmmunitionLeft()){
			
		}
		
	}

}
