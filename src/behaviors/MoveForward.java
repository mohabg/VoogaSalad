package behaviors;

import gameElements.ISpriteProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
/**
 * THIS ENTIRE FILE IS PART OF MY MASTERPIECE
 * 
 * This class is provided as an example of an actual behavior that the sprite can use. As you can see there is not much that is needed 
 * to be done besides calculating the right displacement value based off the angle it is facing, and setting it appropriately. 
 * @author mohabgabal
 *
 */
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
		properties.setX(properties.getX() + getXDiff(actions));
		properties.setY(properties.getY() + getYDiff(actions));
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
