package collisions;

import behaviors.IActions;
import events.Executable;
import level.LevelProperties;

import java.lang.reflect.Method;

/**
 * Handles overall collisions
 */

public class CollisionHandler implements Executable {
	
	private Collision collisionOne;
	private Collision collisionTwo;

	public CollisionHandler() {
		this(new DamageCollision(), new CriticalHitCollision());
	}

	public CollisionHandler(Collision collisionOne, Collision collisionTwo) {
		this.collisionOne = collisionOne;
		this.collisionTwo = collisionTwo;
	}

	public Collision getCollisionOne() {
		return collisionOne;
	}

	public void setCollisionOne(Collision collisionOne) {
		this.collisionOne = collisionOne;
	}

	public Collision getCollisionTwo() {
		return collisionTwo;
	}

	public void setCollisionTwo(Collision collisionTwo) {
		this.collisionTwo = collisionTwo;
	}

	/**
	 * @param one triggers the handleCollision methods of Collisions one and two
	 */
	private void applyCollision(Collision one, Collision two, LevelProperties levelProperties){
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
            //TODO: Throw exception
        }
		return false;
	}

	@Override
	public void execute(IActions action, LevelProperties levProps) {
		// TODO Auto-generated method stub
		applyCollision(collisionOne,collisionTwo,levProps);
	}

    @Override
    public void stop(IActions actions, LevelProperties levProps) {

    }


}
