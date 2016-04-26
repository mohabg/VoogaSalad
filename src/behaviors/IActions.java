package behaviors;

import authoringEnvironment.RefObject;
import gameElements.Sprite;
import gameElements.SpriteProperties;

public interface IActions {

	public Sprite makeSprite(double x, double y, RefObject myRef);
	public Sprite makeSprite(double x, double y, SpriteProperties clone, RefObject myRef);
	public SpriteProperties getSpriteProperties();
	public boolean isUserAction();
	public boolean spriteCanMove();
}
