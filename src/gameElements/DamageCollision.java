package gameElements;

import javafx.beans.property.DoubleProperty;

public class DamageCollision extends Collision{
	
	public DamageCollision(double value) {
		super(value);
	}
	protected void handleCollision(EnemyCollision collision){
		if(collision.isCollidingWithUser(this)){
			causeDamage(collision.getSprite(), getValue());
		}
	}
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
