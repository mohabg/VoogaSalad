package behaviors;

import gameElements.Sprite;
import gameElements.SpriteProperties;

public class ThrustHorizontal extends Movement{
	
	public ThrustHorizontal(double value){
		super(value);
	}

	@Override
	public void move(IActions actions) {
		SpriteProperties properties = actions.getSpriteProperties();
		double currentXVelocity = properties.getMyXvel().doubleValue();
		double currentYVelocity = properties.getMyYvel().doubleValue();
		double xOrientation = Math.cos(Math.toRadians(properties.getMyAngle()));
		double yOrientation = Math.sin(Math.toRadians(properties.getMyAngle()));
		properties.setMyXvel(currentXVelocity + xOrientation * getValue());
		properties.setMyYvel(currentYVelocity + yOrientation * getValue());
	}

}
