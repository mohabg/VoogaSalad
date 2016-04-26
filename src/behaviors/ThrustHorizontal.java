package behaviors;

import gameElements.Sprite;

public class ThrustHorizontal extends Movement{
	
	public ThrustHorizontal(double value){
		super(value);
	}

	@Override
	public void move(Sprite sprite) {
		double currentXVelocity = sprite.getSpriteProperties().getMyXvel().doubleValue();
		double currentYVelocity = sprite.getSpriteProperties().getMyYvel().doubleValue();
		double xOrientation = Math.cos(Math.toRadians(sprite.getAngle().getValue()));
		double yOrientation = Math.sin(Math.toRadians(sprite.getAngle().getValue()));
		sprite.getSpriteProperties().setMyXvel(currentXVelocity + xOrientation * getValue());
		sprite.getSpriteProperties().setMyYvel(currentYVelocity + yOrientation * getValue());
	}

}
