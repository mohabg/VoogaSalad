
package behaviors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import collisions.Collision;
import collisions.DamageCollision;
import collisions.DissapearCollision;
import collisions.PointsCollision;
import gameElements.ExecuteConditions;
import gameElements.Health;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;
import gameplayer.SpriteFactory;
import level.LevelProperties;

/**
 * Describes the type of attack where ammunition from a sprite is fired. When applied, a bullet will come out. 
 */

public class Gun extends Attack{

	public Gun(){
		this(new RefObject("pictures/shootbullet.png"));
	}
	public Gun(RefObject myRef){
		super(myRef);
		this.getBehaviorConditions().setFrequency(20);
		this.getBehaviorConditions().setProbability(0);
	}
	/**
	 * @param sprite The Sprite who's weapon you want to activate
	 */
    @Override
    public void shoot(IActions actions, LevelProperties levProps) {
    		ISpriteProperties properties = actions.getSpriteProperties();
        	ISprite bullet = actions.makeSprite(properties.getX(), properties.getY(), getMyRef());
            bullet.setUserControlled(actions.isUserAction());
        	Behavior movement = new ThrustVertical(-75);
        	actions.setSprite(bullet);
        	bullet.addCollision(new DamageCollision(10));
        	bullet.addCollision(new DissapearCollision());
        	bullet.addCollision(new PointsCollision(10));
            movement.apply(actions,levProps);
        	setAmmunition(getAmmunition() - 1);
    }
	@Override
	public void stop(IActions actions, LevelProperties levProps) {
		// TODO Auto-generated method stub
		
	}
}