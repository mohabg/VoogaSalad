package level;

import collisions.Collision;
import gameElements.ISprite;
import gameElements.SpriteMap;

public interface ILevelProperties {
	
	public SpriteMap getSpriteMap();
	public void setCollidingSprites(ISprite spriteArr, ISprite spriteArr2);
	public ISprite getSpriteForCollision(Collision collision);
	public void addScore(int val);
}
