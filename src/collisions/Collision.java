package collisions;

import java.lang.reflect.Method;

import behaviors.IActions;
import events.Executable;
import events.Trigger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * Describes the different behaviors of collision. Has the sprite that is colliding as an instance variable, 
 * @see applyEffects
 * @see handleCollision
 */

import gameElements.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import level.LevelProperties;

public abstract class Collision implements Executable {
	
	private DoubleProperty value;
	
	public Collision(){
		this(0);
	}
	
	public Collision(double value){
		this.value = new SimpleDoubleProperty(value);
	}
	
	public abstract void execute(IActions action, LevelProperties levProps);
	
	public double getValue(){
		return this.value.doubleValue();
	}
	
	public boolean isCollidingWithUser(LevelProperties levelProperties){
		Sprite collidingSprite = levelProperties.getSpriteForCollision(this);
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
			}
		}
	}
	
	protected Method getCollisionEffects(Collision one, Collision two, LevelProperties levelProperties) {
		Class CollisionOneClass = one.getClass();
		Class CollisionTwoClass = two.getClass();
		try{
			Method method = CollisionOneClass.getMethod("handleCollision", CollisionTwoClass, levelProperties.getClass());
			return method;
		}
		catch(Exception e){
			
		}
		return null;
	}
	
}