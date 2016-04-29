package events;

import java.util.Collection;

import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import gameElements.ISprite;
import gameElements.Sprite;
import level.LevelProperties;

public class CollisionEvent extends Event {
	
	private String spriteOneType;
	private String spriteTwoType;

	public CollisionEvent(String typeOne, String typeTwo, Collision one, Collision two) {
		setExecutable(new CollisionHandler(one,two));
		setTrigger(new CollisionChecker());
		spriteOneType = typeOne;
		spriteTwoType = typeTwo;
	}

	@Override
	public void doEvent(IActions action, LevelProperties levProps) {
		CollisionChecker checker = (CollisionChecker) getTrigger();
		Collection<ISprite> spriteSet = levProps.getSpriteMap().getSprites();
		ISprite[] spriteArr = new ISprite[spriteSet.size()];
		int index = 0;
		for (ISprite sprite : spriteSet) {
			spriteArr[index] = sprite;
			index++;
		}
		for (int i = 0; i < spriteSet.size(); i++) {
			for (int j = 0; j < spriteSet.size(); j++) {
				if (spriteArr[i].getMyRef().equals(spriteOneType) && spriteArr[j].getMyRef().equals(spriteTwoType)) {
					checker.setSpriteOne(spriteArr[i]);
					checker.setSpriteTwo(spriteArr[j]);
					checker.checkColliding();
					levProps.setCollidingSprites(spriteArr[i], spriteArr[j]);
					if ( checker.isTriggered()) {
						getExecutable().execute(action, levProps);
					}
				}
			}
		}
	}
	
	

}
