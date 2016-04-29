package events;

import java.util.Collection;
import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
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
		this(new SimpleStringProperty(" "), new SimpleStringProperty(" "), 
				new DamageCollision(), new DamageCollision());
	}

	public CollisionEvent(StringProperty typeOne, StringProperty typeTwo, Collision one, Collision two) {
		collisionOne = one;
		collisionTwo = two;
		setExecutable(new CollisionHandler(collisionOne,collisionTwo));
		setTrigger(new CollisionChecker());
		spriteOneType = typeOne;
		spriteTwoType = typeTwo;
	}

	@Override
	public void doEvent(IActions action, LevelProperties levProps) {
		CollisionChecker checker = (CollisionChecker) getTrigger();
		Collection<Sprite> spriteSet = levProps.getSpriteMap().getSprites();
		Sprite[] spriteArr = new Sprite[spriteSet.size()];
		int index = 0;
		for (Sprite sprite : spriteSet) {
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
