package behaviors;

import gameElements.ISpriteProperties;

public class ThrustForward extends MoveForward{

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
