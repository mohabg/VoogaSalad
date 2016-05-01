 
package behaviors;

import authoringEnvironment.RefObject;
import gameElements.ISprite;
import gameElements.ISpriteProperties;
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
	}
	/**
	 * @param actions The Sprite who's weapon you want to activate
	 */
    @Override
    public void shoot(IActions actions) {
    		ISpriteProperties properties = actions.getSpriteProperties();
        	ISprite bullet = actions.makeSprite(properties.getX(), properties.getY(), getMyRef());
            bullet.setUserControlled(actions.isUserAction());
            getMovement().enable();
            bullet.addBehavior(getMovement());
        	setAmmunition(getAmmunition() - 1);
    }

	@Override
	public Behavior getClone() {
		return new Gun(this.getMyRef());
	}
}