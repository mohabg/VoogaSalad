package collisions;

import behaviors.Behavior;
import behaviors.Defense;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import level.LevelProperties;

/**
 * Refers to methods that damage the health of a Sprite upon collision. 
 * The handleCollision in this method deals the damage.
 */


public class DamageCollision extends Collision{
	
	public DamageCollision() {
		this(new Sprite());
	}
	
	public DamageCollision(Sprite sprite) {
		super(sprite);
	}
	
	public DamageCollision(Sprite sprite, double value) {
		super(sprite, value);
	}
	
	/**
	 * @param collision The Enemy sprite that you want to cause damage to
	 */
	public void handleCollision(EnemyCollision collision, LevelProperties levelPropertiess){
		if(collision.isCollidingWithUser(this)){
			causeDamage(collision.getSprite(), getValue());
		}
	}
	
	/**
	 * @param collision The Actor sprite that you want to cause damage to
	 */
	public void handleCollision(ActorCollision collision, LevelProperties levelProperties){
		if( !(collision.isCollidingWithUser(this)) ){
			causeDamage(collision.getSprite(), getValue());
		}
	}
	
	/**
	 * @param spriteToDamage Sprite that the damage will be inflicted upon
	 * @param damage Double that indicates how much damage will be inflicted on spriteToDamage
	 */
	protected void causeDamage(Sprite spriteToDamage, double damage) {
		
		//Damage defense before health
		for(Behavior behavior : spriteToDamage.getBehaviors().values()){
			if(behavior instanceof Defense){
				Defense defense = (Defense) behavior;
				if(defense.isEnabled()){
					defense.takeDamage(damage);
					return;
				}
			}
		}
		spriteToDamage.getHealth().decrementHealth(damage);
	}
}
