package collisions;

import behaviors.Behavior;
import behaviors.Defense;
import behaviors.IActions;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
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
		if(this.isCollidingWithUser(levelProperties)){
			Sprite collidingSprite = levelProperties.getSpriteForCollision(collision);
			collidingSprite.takeDamage(getValue());
		}
	}
	
	/**
	 * @param collision The Actor sprite that you want to cause damage to
	 */
	public void handleCollision(ActorCollision collision, LevelProperties levelProperties){
		if( !(this.isCollidingWithUser(levelProperties)) ){
			Sprite collidingSprite = levelProperties.getSpriteForCollision(collision);
			collidingSprite.takeDamage(getValue());
		}
	}
	
	/**
	 * @param spriteToDamage Sprite that the damage will be inflicted upon
	 * @param damage Double that indicates how much damage will be inflicted on spriteToDamage
	 */
	private void causeDamage(Sprite spriteToDamage, double damage) {
		spriteToDamage.takeDamage(damage);
	}

	@Override
	public Collision clone() {
		return new DamageCollision(this.getValue());
	}
	public void execute(IActions action, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}
