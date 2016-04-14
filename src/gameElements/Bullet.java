
package gameElements;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;

public class Bullet extends Attack{

   
	public Bullet(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, int ammunition, int chargeTime,
			ApplyBehaviorConditions behaviorConditions, Movement movement) {
		
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef, ammunition, chargeTime, behaviorConditions, movement);

	}
	
    @Override
    public void apply(Sprite sprite) {
        if(readyToShoot(sprite)){
        	//Keep instance of sprite?
            this.setCoord(sprite.getX(), sprite.getY());
            getMovement().apply(this);
            setAmmunition(getAmmunition() - 1);
        }
    }
}