package collisions;

import java.lang.reflect.Method;
import javafx.beans.property.DoubleProperty;
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

public abstract class Collision{
	
	private Sprite sprite; 
	private DoubleProperty value;
	
	public Collision(Sprite sprite){
		value = new SimpleDoubleProperty(0);
		this.sprite = sprite;
	}
	
	public Collision(Sprite sprite, double value){
		this(sprite);
		this.value.set(value);
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public double getValue(){
		return this.value.doubleValue();
	}
	public boolean isCollidingWithUser(Collision other){
		return other.getSprite().isUserControlled();
	}
	protected void handleCollision(Collision other, LevelProperties levelProperties, LevelProperties levelProperties2){
		//Subclasses should overload this method 
		applyEffects(this, other, levelProperties);
		applyEffects(other, this, levelProperties);
	}

	
	
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