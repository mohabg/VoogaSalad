
package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import collisions.DamageCollision;
import collisions.DissapearCollision;
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
		this(new RefObject("pictures/galaga_enemy_3.png"));
	}
	public Bullet(RefObject myRef){
		super(myRef);
	}
	/**
	 * @param sprite The Sprite who's weapon you want to activate
	 */
    @Override
    public void apply(Sprite sprite, SpriteFactory spriteFactory) {
       
        	Sprite bullet = spriteFactory.makeSprite(sprite.getX().doubleValue(), sprite.getY().doubleValue(), getMyRef());
            bullet.setAsUserControlled();
        	Behavior movement = new MoveVertically(-3);
            bullet.addBehavior(movement.getClass().getName(), movement);
            bullet.addCollision(new DamageCollision(10));
            bullet.addCollision(new DissapearCollision());
        	setAmmunition(getAmmunition() - 1);
        
    }
}