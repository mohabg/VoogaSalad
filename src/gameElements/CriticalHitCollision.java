package gameElements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Describes dealing a "critical hit" in a sprite. The DoubleProperty informs the class of exactly how much damage to deal the
 * sprite should the "critical hit" take. 
 */


public class CriticalHitCollision extends DamageCollision{
	
	private DoubleProperty criticalHitDamage;
	
	public CriticalHitCollision(double value, double criticalHitDamage){
		super(value);
		this.criticalHitDamage = new SimpleDoubleProperty();
		this.criticalHitDamage.set(criticalHitDamage);
	}
	
	public double getCriticalHitDamage(){
		return criticalHitDamage.doubleValue();
	}
	
	/**
	 * @param collision The automated sprite that the user controlled sprite is colliding into
	 **/
	@Override
	protected void handleCollision(EnemyCollision collision){
		if(collision.isCollidingWithUser(this)){
			if(Math.random() < getValue()){
				causeDamage(collision.getSprite(), getCriticalHitDamage());
			}
		}
	}
	/**
	 * @param collision Human controlled sprite that is colliding
	 */
	@Override
	protected void handleCollision(ActorCollision collision){
		if( !(collision.isCollidingWithUser(this)) ){
			if(Math.random() < getValue()){
				causeDamage(collision.getSprite(), getCriticalHitDamage());
		}
		}
	}
}
