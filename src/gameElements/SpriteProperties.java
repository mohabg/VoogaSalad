package gameElements;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author David Yan, Joe Jacob, Huijia Yu, Mohab Gabal
 */

public class SpriteProperties implements ISpriteProperties{
	private DoubleProperty myY;
    private DoubleProperty myX;
    @IgnoreField
    private DoubleProperty myRelativeX; 
    @IgnoreField
    private DoubleProperty myRelativeY;
    @IgnoreField
    private DoubleProperty myXvel;
    @IgnoreField
    private DoubleProperty myYvel;
    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private DoubleProperty myAngle;
    private DoubleProperty myLeftLimit;
    private DoubleProperty myUpLimit;
    private DoubleProperty myRightLimit;
    private DoubleProperty myDownLimit;
    private BooleanProperty myUserControlled;
    private BooleanProperty canMove;
    
    public SpriteProperties(){
        myX = new SimpleDoubleProperty(0);
        myY = new SimpleDoubleProperty(0);
        myXvel = new SimpleDoubleProperty(0);
        myYvel = new SimpleDoubleProperty(0);
        myWidth = new SimpleDoubleProperty(0);
        myHeight = new SimpleDoubleProperty(0);
        myAngle = new SimpleDoubleProperty(0);
        myUserControlled = new SimpleBooleanProperty(false);
        myLeftLimit=new SimpleDoubleProperty(0);
        myRightLimit=new SimpleDoubleProperty(530);
        myDownLimit=new SimpleDoubleProperty(660);
        myUpLimit=new SimpleDoubleProperty(0);
        myRelativeX = new SimpleDoubleProperty(0);
        myRelativeY = new SimpleDoubleProperty(0);
        canMove = new SimpleBooleanProperty(true);
    }

    public SpriteProperties(double x, double y, double width, double height, double angle){
    	this();
        myX.set(x);
        myY.set(y);
        myWidth.set(width);
        myHeight.set(height);
        myAngle.set(angle);
        this.setMyRelativeX(x);
        this.setMyRelativeY(y);
    }

    public SpriteProperties(double x, double y, double xVel, double yVel, double width, double height, double angle,double leftLimit, double rightLimit,
    		double upLimit, double downLimit){
    	this(x, y, width, height, angle);
        myXvel.set(xVel);
        myYvel.set(yVel);
        myLeftLimit.set(leftLimit);
        myRightLimit.set(rightLimit);
        myDownLimit.set(upLimit);
        myDownLimit.set(downLimit);
    }
    
    public SpriteProperties(double x, double y, double xVel, double yVel,
			double width, double height, double angle) {
    	this(x, y, width, height, angle);
		myXvel.set(xVel);
		myYvel.set(yVel);
	}

	public boolean canMove() {
		return canMove.getValue();
	}

	public void disableMovement() {
		canMove.set(false);
	}

	public void enableMovement() {
		canMove.set(true);
	}

	public SpriteProperties getClone(){
    	SpriteProperties clone = new SpriteProperties(myX.doubleValue(), myY.doubleValue(), myXvel.doubleValue(), 
    			myYvel.doubleValue(), myWidth.doubleValue(), myHeight.doubleValue(), myAngle.doubleValue());
    	return clone;
    }
	public void updatePos(){
    	myX.setValue(myX.getValue() + myXvel.getValue());
    	myY.setValue(myY.getValue() + myYvel.getValue());
    }
	public void stayInBounds() {
		double myX = this.getX();
		double myY = this.getY();
		if (myX + getWidth() > getRightLimit()){
    		reverseXVel();    	
		}
    	if (myX < getLeftLimit()){
    		reverseXVel();
    	}
    	if (myY < getUpLimit()){
    		reverseYVel();
    	}
    	if (myY + getHeight() > getDownLimit()){
    		reverseYVel();
    	}
	}

	private void reverseYVel() {
		setYVel(getYVel() * -1);
	}

	private void reverseXVel() {
		setXVel(getXVel() * -1);
	}
    
    public boolean isOutOfBounds(){
    	return myX.getValue() > myRightLimit.getValue() ||   	
    			myX.getValue() + myWidth.doubleValue() < myLeftLimit.getValue() ||
    				myY.getValue()  > myDownLimit.getValue() ||
    					myY.getValue() + myHeight.doubleValue() < myUpLimit.getValue();
    }
	public void setXVel(double Xvel) {
		myXvel.set(Xvel);
	}
	
	public void setYVel(double Yvel) {
		myYvel.setValue(Yvel);
	}
    public double getAngle() {
        return myAngle.get();
    }

    public void setAngle(double myAngle) {
        this.myAngle.set(myAngle % 360);
    }
   
    public DoubleProperty getMyX() {
        return myX;
    }

    public void setX(double myX) {
        this.myX.set(myX);
    }
    public double getX(){
    	return this.myX.doubleValue();
    }

    public double getWidth() {
        return myWidth.doubleValue();
    }
    
    public void setWidth(double myWidth) {
        this.myWidth.set(myWidth);
    }
    
    public double getHeight() {
        return myHeight.doubleValue();
    }
    
    public void setHeight(double myHeight) {
        this.myHeight.set(myHeight);
    }

	public void setY(double y) {
		myY.set(y);
	}
	
	public double getY(){
		return this.myY.doubleValue();
	}
	public boolean isUserControlled() {
		return myUserControlled.getValue();
	}

	public void setUserControlled(boolean userControlled) {
		this.myUserControlled.set(userControlled);
	}

	public double getMyRelativeX() {
		return myRelativeX.doubleValue();
	}

	public void setMyRelativeX(double myRelativeX) {
		this.myRelativeX.set(myRelativeX);
	}

	public double getMyRelativeY() {
		return myRelativeY.doubleValue();
	}

	public void setMyRelativeY(double myRelativeY) {
		this.myRelativeY.set(myRelativeY);
	}

	public BooleanProperty getUserControlledProperty() {
		return this.myUserControlled;
	}
	@Override
	public double getXVel() {
		return this.myXvel.doubleValue();
	}

	@Override
	public double getYVel() {
		return this.myYvel.doubleValue();
	}

	@Override
	public double getRightLimit() {
		return this.myRightLimit.doubleValue();
	}

	@Override
	public double getLeftLimit() {
		return this.myLeftLimit.doubleValue();
	}

	@Override
	public double getUpLimit() {
		return this.myUpLimit.doubleValue();
	}

	@Override
	public double getDownLimit() {
		return this.myDownLimit.doubleValue();
	}

	@Override
	public DoubleProperty getXProperty() {
		return this.myX;
	}

	@Override
	public DoubleProperty getYProperty() {
		return this.myY;
	}

	@Override
	public Property<Number> getHeightProperty() {
		return this.myHeight;
	}

	@Override
	public Property<Number> getWidthProperty() {
		return this.myWidth;
	}

	@Override
	public Property<Number> getAngleProperty() {
		return this.myAngle;
	}

}
