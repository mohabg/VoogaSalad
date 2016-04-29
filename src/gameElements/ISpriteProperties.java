package gameElements;

import javafx.beans.property.DoubleProperty;

public interface ISpriteProperties {
	
	public double getX();
	public double getXVel();
	public double getMyRelativeX();

	public double getY();
	public double getYVel();
	public double getMyRelativeY();

	public double getWidth();
	public double getHeight();
	public double getAngle();

	public void setX(double x);
	public void setXVel(double xVel);
	public void setY(double y);
	public void setYVel(double yVel);
	public void setWidth(double imageWidth);
	public void setHeight(double imageHeight);
	public void setAngle(double angle);
	
	public boolean isUserControlled();
	
	public DoubleProperty getXProperty();
	public DoubleProperty getYProperty();
	public DoubleProperty getHeightProperty();
	public DoubleProperty getWidthProperty();
	public DoubleProperty getAngleProperty();
	
	public ISpriteProperties getClone();
	public void setUserControlled(boolean bool);
	public boolean isOutOfBounds();
	public void disableMovement();
	public void updatePos();
	public boolean canMove();
}
