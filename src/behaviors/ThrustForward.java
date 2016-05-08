package behaviors;

import gameElements.ISpriteProperties;

public class ThrustForward extends MoveForward{
/**
 * THIS ENTIRE FILE IS PART OF MY MASTERPIECE
 * 
 * This class is provided as another example of a behavior the sprite can use, but also to demonstrate the usefulness of the behavior
 * hierarchy. This "thrust" movement differs from the "move" movement in that the velocities are explicitly changing, not the position. 
 * As you can see, an extremely small amount of work needs to be done to achieve this effect. Thus, using the hierarchy, adding and 
 * extending new behaviors is made very easy to do.
 */
	public ThrustForward(){
		this(0);
	}
	public ThrustForward(double value){
		super(value);
	}
	
	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		properties.setXVel(this.getXDiff(actions));
		properties.setYVel(this.getYDiff(actions));
	}

	@Override
	public Behavior getClone() {
		return new ThrustForward(getValue());
	}

}
