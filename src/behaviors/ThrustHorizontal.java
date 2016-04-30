package behaviors;

import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.SpriteProperties;

public class ThrustHorizontal extends Movement{
	
	public ThrustHorizontal() {
		super();
	}
	
	public ThrustHorizontal(double value){
		super(value);
	}

	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		double currentXVelocity = properties.getXVel();
		double currentYVelocity = properties.getYVel();
		double xOrientation = Math.cos(Math.toRadians(properties.getAngle()));
		double yOrientation = Math.sin(Math.toRadians(properties.getAngle()));
		properties.setXVel(currentXVelocity + xOrientation * getValue());
		properties.setYVel(currentYVelocity + yOrientation * getValue());
	}

}
