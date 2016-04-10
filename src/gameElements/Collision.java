package gameElements;

import java.lang.reflect.Method;

public class Collision implements Behavior{
	
	private Sprite spriteProperties; 
	
	public Sprite getSprite() {
		return spriteProperties;
	}

	public void setSpriteProperties(Sprite spriteProperties) {
		this.spriteProperties = spriteProperties;
	}

	@Override
	public void apply(Sprite spriteProperties) {
		Class thisCollisionClass = this.getClass();
		Class spriteCollisionClass = spriteProperties.getCollision().getClass();
		try{
			Method method = thisCollisionClass.getMethod("handleCollision", spriteCollisionClass);
			method.invoke(thisCollisionClass, spriteProperties.getCollision());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean ready() {
		return false;
	}
	
}
