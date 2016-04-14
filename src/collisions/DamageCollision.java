package collisions;

import behaviors.Behavior;
import behaviors.Defense;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;

/**
 * Refers to methods that damage the health of a Sprite upon collision. The handleCollision in this method deals the damage. 
 */


public class DamageCollision extends Collision{
	
	public DamageCollision() {
		
	}
	
	public DamageCollision(double value) {
		super(value);
	}
	
	/**
	 * @param collision The Enemy sprite that you want to cause damage to
	 */
	protected void handleCollision(EnemyCollision collision){
		if(collision.isCollidingWithUser(this)){
			causeDamage(collision.getSprite(), getValue());
		}
	}
	
	/**
	 * @param collision The Actor sprite that you want to cause damage to
	 */
	protected void handleCollision(ActorCollision collision){
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
