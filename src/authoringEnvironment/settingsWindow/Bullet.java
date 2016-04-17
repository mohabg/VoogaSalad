
package authoringEnvironment.settingsWindow;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import behaviors.Attack;
import behaviors.Behavior;
import behaviors.Movement;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;

/**
 * Describes the type of attack where ammunition from a sprite is fired. When applied, a bullet will come out. 
 */

public class Bullet extends Attack{

   
	public Bullet(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef, int ammunition, int chargeTime,
			ApplyBehaviorConditions behaviorConditions, Movement movement) {
		
		super(myProperties, myHealth, myCollisions, myBehaviors, myRef, ammunition, chargeTime, behaviorConditions, movement);

	}
	
	/**
	 * @param sprite The Sprite who's weapon you want to activate
	 */
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