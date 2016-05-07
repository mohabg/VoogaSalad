package events;

import behaviors.IActions;
import collisions.Collision;
import collisions.CollisionChecker;
import collisions.CollisionHandler;
import collisions.DamageCollision;
import gameElements.ISprite;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import level.ILevelProperties;
import level.LevelProperties;

import java.util.Collection;
import java.util.Map;
/**
 * 
 * @author gauravkumar
 *
 */
//THIS ENTIRE FILE IS A PART OF MY MASTERPIECE
//GAURAV KUMAR
public class CollisionEvent extends Event {
	
	private StringProperty spriteOneType;
	private StringProperty spriteTwoType;
	
	public CollisionEvent() {
		this(" ", " ", new DamageCollision(), new DamageCollision());
	}

	public CollisionEvent(String typeOne, String typeTwo, Collision one, Collision two) {
		super("", new CollisionChecker(), new CollisionHandler(one, two));
		spriteOneType = new SimpleStringProperty(typeOne);
		spriteTwoType = new SimpleStringProperty(typeTwo);
	}

	

	@Override
	public void doEvent(IActions action, ILevelProperties levProps) {
		CollisionChecker checker = (CollisionChecker) getTrigger();
		Map<Integer, ISprite> sprites = levProps.getSpriteMap().getSpriteMap();
		for ( Integer i = 0; i < sprites.size(); i++) {
			for ( Integer j = 0; j < sprites.size(); j++) {
				if (sprites.containsKey(i) && sprites.containsKey(j) && i != j) {
					if (sprites.get(i).getMyRef().trim().equals(spriteOneType.get().trim()) && 
							sprites.get(j).getMyRef().trim().equals(spriteTwoType.get().trim())) {
						checker.checkColliding(sprites.get(i),sprites.get(j));
						if ( checker.isTriggered()) { 
							levProps.setCollidingSprites(sprites.get(i), sprites.get(j));
							CollisionHandler handler = (CollisionHandler) this.getExecutable();
							sprites.get(i).addCollision(handler.getCollisionOne());
							sprites.get(j).addCollision(handler.getCollisionTwo());
							getExecutable().execute(action, levProps);
						}
					}
				}
			}
		}
	}
	
	

}
