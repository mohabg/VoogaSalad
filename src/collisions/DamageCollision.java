package collisions;

import behaviors.Behavior;
import behaviors.Defense;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;

public class DamageCollision extends Collision{
	
	public DamageCollision() {
		
	}
	
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
