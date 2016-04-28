package collisions;

import java.lang.reflect.Method;
import java.util.Collection;

import behaviors.IActions;
import events.Executable;
import gameElements.Sprite;
import gameElements.SpriteMap;

/**
 * Handles overall collisions
 */

import level.LevelProperties;

public class CollisionHandler implements Executable {
    
    private Collision collisionOne;
    private Collision collisionTwo;
    
    public CollisionHandler(){
        
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
     * @see haveCollisionEffects
     * @see handleCollision
     */
    private void applyCollision(Collision one, Collision two, LevelProperties levelProperties){
        if(haveCollisionEffects(one, two, levelProperties)){
            one.handleCollision(two, levelProperties);
        }
        if(haveCollisionEffects(two, one, levelProperties)){
            two.handleCollision(one, levelProperties);
        }
    }
    
    /*	public void checkCollisions(LevelProperties levelProperties, CollisionChecker checker) {
     //CollisionChecker checker = new CollisionChecker();
     Collection<Sprite> spriteSet = levelProperties.getSpriteMap().getSprites();
     Sprite[] spriteArr = new Sprite[spriteSet.size()];
     int index = 0;
     for (Sprite sprite : spriteSet) {
     spriteArr[index] = sprite;
     index++;
     }
     for (int i = 0; i < spriteSet.size(); i++) {
     for (int j = i + 1; j < spriteSet.size(); j++) {
     if (checker.areColliding(spriteArr[i], spriteArr[j])) {
					haveCollided(levelProperties, spriteArr, i, j);
     }
     }
     }
     }
     
     private void haveCollided(LevelProperties levelProperties, Sprite[] spriteArr, int i, int j) {
     levelProperties.setCollidingSprites(spriteArr[i], spriteArr[j]);
     for (Collision collisionSpriteOne : spriteArr[i].getCollisions()) {
     for (Collision collisionSpriteTwo : spriteArr[j].getCollisions()) {
     applyCollision(collisionSpriteOne, collisionSpriteTwo,
     levelProperties);
     
     }
     
     }
     }*/
    
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
