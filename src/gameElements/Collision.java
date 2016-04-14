package gameElements;

import java.lang.reflect.Method;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Collision{
	
	private Sprite sprite; 
	private DoubleProperty value;
	
	public Collision(){
		value = new SimpleDoubleProperty();
	}
	public Collision(double value){
		this();
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
	protected void handleCollision(Collision other, LevelProperties levelProperties){
		//Subclasses should overload this method 
		applyEffects(this, other);
		applyEffects(other, this);
	}

	private void applyEffects(Collision one, Collision two) {
		Method methodToInvoke = getCollisionEffects(one, two);
		if(methodToInvoke != null){
			try{
				methodToInvoke.invoke(one, two);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	protected Method getCollisionEffects(Collision one, Collision two) {
		Class CollisionOneClass = one.getClass();
		Class CollisionTwoClass = two.getClass();
		try{
			Method method = CollisionOneClass.getMethod("handleCollision", CollisionTwoClass);
			return method;
		}
		catch(Exception e){
			
		}
		return null;
	}
}