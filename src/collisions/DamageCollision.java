package collisions;

import behaviors.Behavior;
import behaviors.IActions;
import gameElements.ISprite;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import level.ILevelProperties;
import level.LevelProperties;

/**
 * Refers to methods that damage the health of a Sprite upon collision. 
 * The handleCollision in this method deals the damage.
 */


public class DamageCollision extends Collision{
	
	public DamageCollision() {
		this(0);
	}
	
	public DamageCollision(double value) {
		super(value);
	}
	
	
	/**
	 * @param collision The Enemy sprite that you want to cause damage to
	 */
	public void handleCollision(EnemyCollision collision, LevelProperties levelProperties){
		if(!(collision.isCollidingWithUser(levelProperties))){
			damageSprite(collision, levelProperties);
		}
	}
	
	/**
	 * @param collision The Actor sprite that you want to cause damage to
	 */
	public void handleCollision(ActorCollision collision, LevelProperties levelProperties){
		if( collision.isCollidingWithUser(levelProperties) ){
			damageSprite(collision, levelProperties);
		}
	}

	private void damageSprite(Collision collision, LevelProperties levelProperties) {
		ISprite collidingSprite = levelProperties.getSpriteForCollision(collision);
		collidingSprite.takeDamage(getValue());
	}

	@Override
	public Collision clone() {
		return new DamageCollision(this.getValue());
	}
}
