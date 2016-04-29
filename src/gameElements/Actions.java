package gameElements;

import authoringEnvironment.RefObject;
import behaviors.IActions;
import gameplayer.SpriteFactory;

public class Actions implements IActions{
	
	private Sprite sprite;
	private SpriteFactory spriteFactory; 
	
	public Actions(){
	}
	public void setSprite(Sprite sprite){
		this.sprite = sprite;
	}
	public void setSpriteFactory(SpriteFactory spriteFactory){
		this.spriteFactory = spriteFactory;
	}
	@Override
	public Sprite makeSprite(double x, double y, RefObject myRef) {
		return spriteFactory.makeSprite(x, y, myRef);
	}

	@Override
	public Sprite makeSprite(double x, double y, SpriteProperties clone, RefObject myRef) {
		return spriteFactory.makeSprite(x, y, clone, myRef);
	}

	@Override
	public SpriteProperties getSpriteProperties() {
		return sprite.getSpriteProperties();
	}
	@Override
	public boolean isUserAction() {
		return this.sprite.isUserControlled();
	}
	@Override
	public boolean spriteCanMove() {
		return this.getSpriteProperties().canMove();
	}

}
