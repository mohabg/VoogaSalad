package gameElements;

import authoringEnvironment.RefObject;
import behaviors.IActions;
import gameplayer.SpriteFactory;

public class Actions implements IActions{
	
	private ISprite sprite;
	private SpriteFactory spriteFactory; 
	
	public Actions(){
	}
	
	@Override
	public void setSprite(ISprite iSprite){
		this.sprite = iSprite;
	}
	
	public void setSpriteFactory(SpriteFactory spriteFactory){
		this.spriteFactory = spriteFactory;
	}
	
	@Override
	public Sprite makeSprite(double x, double y, RefObject myRef) {
		return spriteFactory.makeSprite(x, y, myRef);
	}

	@Override
	public Sprite makeSprite(double x, double y, ISpriteProperties clone, RefObject myRef) {
		return spriteFactory.makeSprite(x, y, clone, myRef);
	}

	@Override
	public ISpriteProperties getSpriteProperties() {
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
