package collisions;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CriticalHitCollision extends DamageCollision{
	
	private DoubleProperty criticalHitDamage;
	
	public CriticalHitCollision() {
		criticalHitDamage = new SimpleDoubleProperty();
	}
	
	public CriticalHitCollision(double value, double criticalHitDamage){
		super(value);
		this.criticalHitDamage = new SimpleDoubleProperty();
		this.criticalHitDamage.set(criticalHitDamage);
	}
	
	public double getCriticalHitDamage(){
		return criticalHitDamage.doubleValue();
	}
	
	@Override
	protected void handleCollision(EnemyCollision collision){
		if(collision.isCollidingWithUser(this)){
			if(Math.random() < getValue()){
				causeDamage(collision.getSprite(), getCriticalHitDamage());
			}
		}
	}
	@Override
	protected void handleCollision(ActorCollision collision){
		if( !(collision.isCollidingWithUser(this)) ){
			if(Math.random() < getValue()){
				causeDamage(collision.getSprite(), getCriticalHitDamage());
		}
		}
	}
}
