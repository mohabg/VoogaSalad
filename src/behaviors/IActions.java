package behaviors;

import authoringEnvironment.RefObject;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;

public interface IActions {

	public ISprite makeSprite(double x, double y, RefObject myRef);
	public ISpriteProperties getSpriteProperties();
	public boolean isUserAction();
	public boolean spriteCanMove();
	public void setSprite(ISprite sprite);
	public ISprite getSprite();
	public ISprite getTarget();
	public void setTarget(ISprite target);
}
