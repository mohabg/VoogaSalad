package gameElements;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;


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
	
	public double getRightLimit();
	public double getLeftLimit();
	public double getUpLimit();
	public double getDownLimit();
	
	public boolean isUserControlled();
	
	public ISpriteProperties getClone();
	public void setUserControlled(boolean bool);
	public void disableMovement();
	public boolean isOutOfBounds();
	public void stayInBounds();
	public void updatePos();
	public boolean canMove();
	public DoubleProperty getXProperty();
	public DoubleProperty getYProperty();
	public Property<Number> getHeightProperty();
	public Property<Number> getWidthProperty();
	public Property<Number> getAngleProperty();
}
