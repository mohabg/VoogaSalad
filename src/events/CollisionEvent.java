package events;

import java.util.Collection;
import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import gameElements.ISprite;
import collisions.DamageCollision;
import gameElements.Sprite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import level.LevelProperties;

public class CollisionEvent extends Event {
	
	private StringProperty spriteOneType;
	private StringProperty spriteTwoType;
	private Collision collisionOne;
	private Collision collisionTwo;
	
	public CollisionEvent() {
		this(" ", " ", 
				new DamageCollision(), new DamageCollision());
	}
	
	public CollisionEvent(String typeOne, String typeTwo, Collision one, Collision two) {
		collisionOne = one;
		collisionTwo = two;
		setExecutable(new CollisionHandler(collisionOne,collisionTwo));
		setTrigger(new CollisionChecker());
		spriteOneType = new SimpleStringProperty();
		spriteOneType.set(typeOne);
		spriteTwoType = new SimpleStringProperty();
		spriteTwoType.set(typeTwo);
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
				if (spriteArr[i].getMyRef().equals(spriteOneType.get()) && spriteArr[j].getMyRef().equals(spriteTwoType.get())) {
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
