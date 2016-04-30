package collisions;

import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.LevelProperties;

/**
 * Describes dealing a "critical hit" in a sprite.
 *  The DoubleProperty informs the class of exactly how much damage to deal the
 * sprite should the "critical hit" take. The probability of dealing the hit comes from the
 * DoubleProperty in the superclass.
 */


public class CriticalHitCollision extends DamageCollision{
	
	private DoubleProperty criticalHitDamage;
	
	public CriticalHitCollision() {
		this(0, 0);
	}
	
	public CriticalHitCollision(double value, double criticalHitDamage){
		super(value);
		this.criticalHitDamage = new SimpleDoubleProperty(criticalHitDamage);
	}
	
	public double getCriticalHitDamage(){
		return criticalHitDamage.doubleValue();
	}
	
	/**
	 * @param collision The automated sprite that the user controlled sprite is colliding into
	 **/

	public void handleCollision(EnemyCollision collision, LevelProperties levelProperties){
		if(collision.isCollidingWithUser(levelProperties)){
			if(Math.random() < getValue()){
				levelProperties.getSpriteForCollision(collision).takeDamage(getCriticalHitDamage());
			}
		}
	}
	/**
	 * @param collision Human controlled sprite that is colliding
	 */
	
	public void handleCollision(ActorCollision collision, LevelProperties levelProperties){
		if( !(collision.isCollidingWithUser(levelProperties)) ){
			if(Math.random() < getValue()){
				levelProperties.getSpriteForCollision(collision).takeDamage(getCriticalHitDamage());
			}
		}
	}
	
	public Collision clone() {
		return new CriticalHitCollision(getValue(),criticalHitDamage.get());
	}
}
