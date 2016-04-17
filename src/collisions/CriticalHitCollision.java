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
		this(new Sprite(""));
	}
	
	public CriticalHitCollision(Sprite sprite) {
		super(sprite);
		criticalHitDamage = new SimpleDoubleProperty();
	}
	
	public CriticalHitCollision(Sprite sprite, double value, double criticalHitDamage){
		super(sprite, value);
		this.criticalHitDamage = new SimpleDoubleProperty();
		this.criticalHitDamage.set(criticalHitDamage);
	}
	
	public double getCriticalHitDamage(){
		return criticalHitDamage.doubleValue();
	}
	
	/**
	 * @param collision The automated sprite that the user controlled sprite is colliding into
	 **/

	public void handleCollision(EnemyCollision collision, LevelProperties levelPrpperties){
		if(collision.isCollidingWithUser(this)){
			if(Math.random() < getValue()){
				causeDamage(collision.getSprite(), getCriticalHitDamage());
			}
		}
	}
	/**
	 * @param collision Human controlled sprite that is colliding
	 */
	
	public void handleCollision(ActorCollision collision){
		if( !(collision.isCollidingWithUser(this)) ){
			if(Math.random() < getValue()){
				causeDamage(collision.getSprite(), getCriticalHitDamage());
		}
		}
	}
}
