package collisions;

import java.lang.reflect.Method;

/**
 * Handles overall collisions 
 */

import level.LevelProperties;

public class CollisionHandler {

	public CollisionHandler(){
		
	}
	

	/**
	 * @param one triggers the handleCollision methods of Collisions one and two
	 * @see haveCollisionEffects
	 * @see handleCollision
	 */
	public void applyCollision(Collision one, Collision two, LevelProperties levelProperties){
		if(haveCollisionEffects(one, two, levelProperties)){
			one.handleCollision(two, levelProperties);
		}
		if(haveCollisionEffects(two, one, levelProperties)){
			two.handleCollision(one, levelProperties);
		}
	}
	
	/**
	 * @param one determines whether Collision one or two have a handleCollision method, if they do not the method returns false
	 * @param levelProperties 
	 */

	private boolean haveCollisionEffects(Collision one, Collision two, LevelProperties levelProperties) {
		Class<? extends Collision> CollisionOneClass = one.getClass();
		Class<? extends Collision> CollisionTwoClass = two.getClass();
		try{
			Method[] methods = CollisionOneClass.getMethods();
			Method method = CollisionOneClass.getDeclaredMethod("handleCollision", CollisionTwoClass, levelProperties.getClass());
			return true;
		}
		catch(NoSuchMethodException e){
		}
		return false;
	}
}
