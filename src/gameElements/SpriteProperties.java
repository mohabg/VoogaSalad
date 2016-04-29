package gameElements;

import gameElements.ISprite.spriteState;
import goals.Goals;

import java.util.ArrayList;
import java.util.List;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author David Yan, Joe Jacob, Huijia Yu, Mohab Gabal
 */
public class SpriteProperties implements ISpriteProperties{
	
    private DoubleProperty myX;
    private DoubleProperty myRelativeX;
    private DoubleProperty myY;
    private DoubleProperty myRelativeY;
    private DoubleProperty myXvel;
    private DoubleProperty myYvel;
    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private DoubleProperty myAngle;
    private Goals myGoal;
    private DoubleProperty myLeftLimit;
    private DoubleProperty myDownLimit;
    private DoubleProperty myRightLimit;
    private DoubleProperty myUpLimit;
    private BooleanProperty myUserControlled;
    private BooleanProperty canMove;

    @IgnoreField

    private List<ISpriteProperties> myClones;
    
    public SpriteProperties(){
        myX = new SimpleDoubleProperty(0);
        myY = new SimpleDoubleProperty(0);
        myXvel = new SimpleDoubleProperty(0);
        myYvel = new SimpleDoubleProperty(0);
        myWidth = new SimpleDoubleProperty(0);
        myHeight = new SimpleDoubleProperty(0);
        myAngle = new SimpleDoubleProperty(0);
        myUserControlled = new SimpleBooleanProperty(false);
        myClones = new ArrayList<ISpriteProperties>();
        myLeftLimit=new SimpleDoubleProperty(0);
        myRightLimit=new SimpleDoubleProperty(590);
        myUpLimit=new SimpleDoubleProperty(850);
        myDownLimit=new SimpleDoubleProperty(0);
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
        myUpLimit.set(upLimit);
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
    	// If following original (such as shield)
    	// bindCloneProperties(clone);
    	myClones.add(clone);
    	return clone;
    }

	private void bindCloneProperties(SpriteProperties clone) {
		clone.getMyX().bindBidirectional(myX);
    	clone.getYProperty().bindBidirectional(myY);
    	clone.getAngleProperty().bindBidirectional(myAngle);
    	clone.getXVelProperty().bindBidirectional(myXvel);
    	clone.getYVelProperty().bindBidirectional(myYvel);
    	clone.getUserControlledProperty().bindBidirectional(myUserControlled);
	}
	public void updatePos(){
    	if(this.isUserControlled()){
    		stayInBounds();
    	}
    	myX.setValue(myX.getValue() + myXvel.getValue());
    	myY.setValue(myY.getValue() + myYvel.getValue());
    }

	private void stayInBounds() {
		if (myX.getValue()>myRightLimit.getValue())
    		myXvel.setValue(-myXvel.getValue());    	
    	if (myX.getValue()<myLeftLimit.getValue())
    		myXvel.setValue(-myXvel.getValue());
    	if (myY.getValue()>myUpLimit.getValue())
    		myYvel.setValue(-myYvel.getValue());
    	if (myY.getValue()<myDownLimit.getValue())
    		myYvel.setValue(-myYvel.getValue());
	}
    public boolean isOutOfBounds(){
    	return myX.getValue() > myRightLimit.getValue() ||   	
    			myX.getValue() < myLeftLimit.getValue() ||
    				myY.getValue() > myUpLimit.getValue() ||
    					myY.getValue()<myDownLimit.getValue();
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

	public double getMyXLeftLimit() {
		return myLeftLimit.doubleValue();
	}

	public void setMyXLeftLimit(double myXLeftLimit) {
		this.myLeftLimit.set(myXLeftLimit);
	}

	public double getMyYDownLimit() {
		return myDownLimit.doubleValue();
	}

	public void setMyYDownLimit(double myYDownLimit) {
		this.myDownLimit.set(myYDownLimit);
	}

	public double getMyXRightLimit() {
		return myRightLimit.doubleValue();
	}

	public void setMyRightLimit(double myRightLimit) {
		this.myRightLimit.set(myRightLimit);
	}

	public double getMyYUpLimit() {
		return myUpLimit.doubleValue();
	}

	public void setMyYUpLimit(double myYUpLimit) {
		this.myUpLimit.set(myYUpLimit);
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

	public DoubleProperty getYVelProperty() {
		return this.myYvel;
	}

	public DoubleProperty getXVelProperty() {
		return this.myXvel;
	}

	public DoubleProperty getAngleProperty() {
		return this.myAngle;
	}
	
	@Override
	public DoubleProperty getYProperty() {
		return this.myY;
	}

	@Override
	public DoubleProperty getXProperty() {
		return this.myX;
	}

	@Override
	public DoubleProperty getHeightProperty() {
		return this.myHeight;
	}

	@Override
	public DoubleProperty getWidthProperty() {
		return this.myWidth;
	}

	@Override
	public double getXVel() {
		return this.myXvel.doubleValue();
	}

	@Override
	public double getYVel() {
		return this.myYvel.doubleValue();
	}

}
