package gameElements;

import java.lang.reflect.Method;

public abstract class Collision{
	
	private Sprite sprite; 
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	protected void handleCollision(Collision other){
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
			e.printStackTrace();
		}
		return null;
	}
}
