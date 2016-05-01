package collisions;

import gameElements.ISprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.ILevelProperties;
import level.LevelProperties;

import java.lang.reflect.Method;

/**
 * Describes the different behaviors of collision. Has the sprite that is colliding as an instance variable,
 *
 */

public abstract class Collision {
	
	private DoubleProperty value;
	
	public Collision(){
		this(0);
	}
	
	public Collision(double value){
		this.value = new SimpleDoubleProperty(value);
	}
	
	public double getValue(){
		return this.value.doubleValue();
	}
	
	public boolean isCollidingWithUser(ILevelProperties levelProperties){
		ISprite collidingSprite = levelProperties.getSpriteForCollision(this);
		return collidingSprite.isUserControlled();
	}
	public void handleCollision(Collision other, ILevelProperties levelProperties){
		//Subclasses should overload this method 
		applyEffects(this, other, levelProperties);
		applyEffects(other, this, levelProperties);
	}

	public abstract Collision clone();
	
	private void applyEffects(Collision one, Collision two, ILevelProperties levelProperties) {
		Method methodToInvoke = getCollisionEffects(one, two, levelProperties);
		if(methodToInvoke != null){
			try{
				Object[] params = new Object[2];
				params[0] = two;
				params[1] = levelProperties;
				methodToInvoke.invoke(one, params);
			}
			catch(Exception e){
			}
		}
	}
	
	protected Method getCollisionEffects(Collision one, Collision two, ILevelProperties levelProperties) {
		Class CollisionOneClass = one.getClass();
		Class CollisionTwoClass = two.getClass();
		try{
            return CollisionOneClass.getMethod("handleCollision", CollisionTwoClass, levelProperties.getClass());
		}
		catch(Exception e){
        }
		return null;
	}
	
}