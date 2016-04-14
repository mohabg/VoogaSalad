package gameElements;

import java.lang.reflect.Method;

public class CollisionHandler {
	
	public CollisionHandler(){
		//Should have some way to access time and score
	}
	
	public void applyCollision(Collision one, Collision two, LevelProperties levelProperties){
		if(haveCollisionEffects(one, two)){
			one.handleCollision(two, levelProperties);
		}
		if(haveCollisionEffects(two, one)){
			two.handleCollision(one, levelProperties);
		}
	}

	private boolean haveCollisionEffects(Collision one, Collision two) {
		Class CollisionOneClass = one.getClass();
		Class CollisionTwoClass = two.getClass();
		try{
			Method method = CollisionOneClass.getMethod("handleCollision", CollisionTwoClass);
			return true;
		}
		catch(Exception e){
			//e.printStackTrace();
		}
		return false;
	}
}
