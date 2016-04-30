package collisions;

import behaviors.IActions;
import events.Executable;
import gameElements.ISprite;
import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.LevelProperties;

import java.lang.reflect.Method;

/**
 * Describes the different behaviors of collision. Has the sprite that is colliding as an instance variable,
 *
 * @see applyEffects
 * @see handleCollision
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
	
	public boolean isCollidingWithUser(LevelProperties levelProperties){
		ISprite collidingSprite = levelProperties.getSpriteForCollision(this);
		return collidingSprite.isUserControlled();
	}
	public void handleCollision(Collision other, LevelProperties levelProperties){
		//Subclasses should overload this method 
		applyEffects(this, other, levelProperties);
		applyEffects(other, this, levelProperties);
	}

	public abstract Collision clone();
	
	private void applyEffects(Collision one, Collision two, LevelProperties levelProperties) {
		Method methodToInvoke = getCollisionEffects(one, two, levelProperties);
		if(methodToInvoke != null){
			try{
				Object[] params = new Object[2];
				params[0] = two;
				params[1] = levelProperties;
				methodToInvoke.invoke(one, params);
			}
			catch(Exception e){
                //TODO: Throw exception
			}
		}
	}
	
	protected Method getCollisionEffects(Collision one, Collision two, LevelProperties levelProperties) {
		Class CollisionOneClass = one.getClass();
		Class CollisionTwoClass = two.getClass();
		try{
            return CollisionOneClass.getMethod("handleCollision", CollisionTwoClass, levelProperties.getClass());
		}
		catch(Exception e){
            //TODO: Throw exception
        }
		return null;
	}
	
}