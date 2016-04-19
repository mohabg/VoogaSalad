
package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import gameElements.ApplyBehaviorConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import gameplayer.SpriteFactory;

/**
 * Describes the type of attack where ammunition from a sprite is fired. When applied, a bullet will come out. 
 */

public class Bullet extends Attack{

	public Bullet(){
		super();
	}
	public Bullet(RefObject myRef){
		super(myRef);
	}
	/**
	 * @param sprite The Sprite who's weapon you want to activate
	 */
    @Override
    public void apply(Sprite sprite, SpriteFactory spriteFactory) {
        if(readyToShoot(sprite)){
        	spriteFactory.makeSprite(sprite.getX().doubleValue(), sprite.getY().doubleValue(), getMyRef());
            setAmmunition(getAmmunition() - 1);
        }
    }
}