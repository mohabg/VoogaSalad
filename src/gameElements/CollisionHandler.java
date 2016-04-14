package gameElements;

import java.lang.reflect.Method;

public class CollisionHandler {
	
	public CollisionHandler(){
		//Should have some way to access time and score
	}
	
	
	/**
	 * @param one triggers the handleCollision methods of Collisions one and two
	 * @see haveCOllisionEffects
	 * @see handleCollision
	 */
	public void applyCollision(Collision one, Collision two){
		if(haveCollisionEffects(one, two)){
			one.handleCollision(two);
		}
		if(haveCollisionEffects(two, one)){
			two.handleCollision(one);
		}
	}
	
	/**
	 * @param one determines whether Collision one or two have a handleCollision method, if they do not the method returns false
	 */

	private boolean haveCollisionEffects(Collision one, Collision two) {
		Class CollisionOneClass = one.getClass();
		Class CollisionTwoClass = two.getClass();
		try{
			Method method = CollisionOneClass.getMethod("handleCollision", CollisionTwoClass);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
