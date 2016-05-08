package gameElements;

import authoringEnvironment.RefObject;
import behaviors.IActions;
import gameplayer.SpriteFactory;

public class Actions implements IActions{
	
	private ISprite sprite;
	private ISprite target;
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
	public Sprite makeSprite(double x, double y, double angle, RefObject myRef) {
		return spriteFactory.makeSprite(x, y, angle, myRef);
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
	@Override
	public ISprite getTarget() {
		return this.target;
	}
	@Override
	public void setTarget(ISprite target) {
		this.target = target;
	}

	@Override
	public ISprite getSprite() {
		return this.sprite;
	}

}
