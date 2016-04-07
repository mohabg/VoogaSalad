package gameElements;

import java.lang.reflect.Method;

public class Collision implements Behavior{
	
	private Sprite sprite; 
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	@Override
	public void apply(Sprite sprite) {
		Class thisCollisionClass = this.getClass();
		Class spriteCollisionClass = sprite.getCollision().getClass();
		try{
			Method method = thisCollisionClass.getMethod("handleCollision", spriteCollisionClass);
			method.invoke(thisCollisionClass, sprite.getCollision());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
