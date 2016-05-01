package events;

import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import collisions.DamageCollision;
import gameElements.ISprite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import level.LevelProperties;

import java.util.Collection;

public class CollisionEvent extends Event {
	
	private StringProperty spriteOneType;
	private StringProperty spriteTwoType;
	
	public CollisionEvent() {
		this(" ", " ", new DamageCollision(), new DamageCollision());
	}

	public CollisionEvent(String typeOne, String typeTwo, Collision one, Collision two) {
		super("", new CollisionChecker(), new CollisionHandler(one, two));
		//setExecutable(new CollisionHandler(collisionOne, collisionTwo));
		//setTrigger(new CollisionChecker());
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
				if (spriteArr[i].getMyRef().trim().equals(spriteOneType.get().trim()) && spriteArr[j].getMyRef().trim().equals(spriteTwoType.get().trim())) {
					//checker.setSpriteOne(spriteArr[i]);
					//checker.setSpriteTwo(spriteArr[j]);
					checker.checkColliding(spriteArr[i],spriteArr[j]);
					if ( checker.isTriggered()) { 
						levProps.setCollidingSprites(spriteArr[i], spriteArr[j]);
						CollisionHandler handler = (CollisionHandler) this.getExecutable();
						spriteArr[i].addCollision(handler.getCollisionOne());
						spriteArr[j].addCollision(handler.getCollisionTwo());
						getExecutable().execute(action, levProps);
					}
				}
			}
		}
	}
	
	

}
