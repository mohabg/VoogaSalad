package behaviors;

import gameElements.ISpriteProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// This entire file is part of my masterpiece.
// Jesse Rencurrell Pollack

public class MoveForward extends Movement{

	public MoveForward(){
		this(0);
	}
	public MoveForward(double value){
		super(value);
	}
	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSprite().getSpriteProperties();
		double xDisplacement = getXDiff(actions);
		double yDisplacement = getYDiff(actions);
		properties.setX(properties.getX() + xDisplacement);
		properties.setY(properties.getY() + yDisplacement);
	}
	protected double getYDiff(IActions actions) {
		double yDisplacement = Math.cos(Math.toRadians(-1 * actions.getSpriteProperties().getAngle())) * getValue();
		return yDisplacement;
	}
	protected double getXDiff(IActions actions) {
		double xDisplacement = Math.sin(Math.toRadians(-1 * actions.getSpriteProperties().getAngle())) * getValue();
		return xDisplacement;
	}

	@Override
	public Behavior getClone() {
		return new MoveForward(getValue());
	}

}
