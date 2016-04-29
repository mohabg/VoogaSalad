package behaviors;

import authoringEnvironment.RefObject;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;

public interface IActions {

	public ISprite makeSprite(double x, double y, RefObject myRef);
	public ISprite makeSprite(double x, double y, ISpriteProperties iSpriteProperties, RefObject myRef);
	public ISpriteProperties getSpriteProperties();
	public boolean isUserAction();
	public boolean spriteCanMove();
	public void setSprite(ISprite sprite);
	
}
