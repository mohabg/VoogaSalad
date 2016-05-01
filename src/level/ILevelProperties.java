package level;

import gameElements.ISprite;
import gameElements.SpriteMap;

public interface ILevelProperties {
	
	public SpriteMap getSpriteMap();
	public void setCollidingSprites(ISprite spriteArr, ISprite spriteArr2);

}
