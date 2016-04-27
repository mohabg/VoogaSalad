package gameElements;

import gameElements.ISprite.spriteState;
import java.util.ArrayList;
import java.util.List;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.IgnoreField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class SpriteProperties {
    private DoubleProperty myX;
    private DoubleProperty myRelativeX;
    private DoubleProperty myY;
    private DoubleProperty myRelativeY;
    private DoubleProperty myXvel;
    private DoubleProperty myYvel;
    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private DoubleProperty myAngle;
    private DoubleProperty myLeftLimit;
    private DoubleProperty myDownLimit;
    private DoubleProperty myRightLimit;
    private DoubleProperty myUpLimit;
    private BooleanProperty myUserControlled;
    private BooleanProperty canMove;

    @IgnoreField
    private List<SpriteProperties> myClones;
    
    public SpriteProperties(){
        myX = new SimpleDoubleProperty(0);
        myY = new SimpleDoubleProperty(0);
        myXvel = new SimpleDoubleProperty(0);
        myYvel = new SimpleDoubleProperty(0);
        myWidth = new SimpleDoubleProperty(0);
        myHeight = new SimpleDoubleProperty(0);
        myAngle = new SimpleDoubleProperty(0);
        myUserControlled = new SimpleBooleanProperty(false);
        myClones = new ArrayList<SpriteProperties>();
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
        this.setMyRelativeX(new SimpleDoubleProperty(x));
        this.setMyRelativeY(new SimpleDoubleProperty(y));
    }

    public SpriteProperties(double x, double y, double xVel, double yVel, double width, double height, double angle,double leftLimit, double rightLimit,
    		double upLimit, double downLimit){
    	this(x, y, width, height, angle);
       // myX.set(x);
       //  myY.set(y);
        myXvel.set(xVel);
        myYvel.set(yVel);
      //  myWidth.set(width);
      //  myHeight.set(height);
      //  myAngle.set(angle);
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
    	bindCloneProperties(clone);
    	myClones.add(clone);
    	return clone;
    }

	private void bindCloneProperties(SpriteProperties clone) {
		clone.getMyX().bindBidirectional(myX);
    	clone.getMyY().bindBidirectional(myY);
    	clone.getMyAngleProperty().bindBidirectional(myAngle);
    	clone.getMyXvel().bindBidirectional(myXvel);
    	clone.getMyYvel().bindBidirectional(myYvel);
    	clone.getUserControlled().bindBidirectional(myUserControlled);
	}
    
    public void updatePos(){
    	if (myX.getValue()>myRightLimit.getValue())
    		myXvel.setValue(-myXvel.getValue());    	
    	if (myX.getValue()<myLeftLimit.getValue())
    		myXvel.setValue(-myXvel.getValue());
    	if (myY.getValue()>myUpLimit.getValue())
    		myYvel.setValue(-myYvel.getValue());
    	if (myY.getValue()<myDownLimit.getValue())
    		myYvel.setValue(-myYvel.getValue());
    	myX.setValue(myX.getValue() + myXvel.getValue());
    	myY.setValue(myY.getValue() + myYvel.getValue());
    }

    public DoubleProperty getMyXvel() {
		return myXvel;
	}

	public void setMyXvel(double Xvel) {
		myXvel.set(Xvel);
	}

	public void setMyXvelProperty(DoubleProperty myXvel) {
		this.myXvel = myXvel;
	}

	public DoubleProperty getMyYvel() {
		return myYvel;
	}

	public void setMyYvel(double Yvel) {
		myYvel.setValue(Yvel);
	}
	public void setMyYvelProperty(DoubleProperty myYvel) {
		this.myYvel = myYvel;
	}

    public double getMyAngle() {
        return myAngle.get();
    }
 
    public DoubleProperty getMyAngleProperty() {
        return myAngle;
    }

    public void setMyAngle(double myAngle) {
        this.myAngle.set(myAngle % 360);
    }
    public void setMyAngleProperty(DoubleProperty myAngle) {
       this.myAngle = myAngle;
    }

    public DoubleProperty getMyX() {
        return myX;
    }

    public DoubleProperty myXProperty() {
        return myX;
    }

    public void setMyX(double myX) {
        this.myX.set(myX);
    }

    public void setMyXProperty(DoubleProperty X){
    	myX = X;
    }

    public void setMyYProperty(DoubleProperty Y){
    	myY = Y;
    }

    public DoubleProperty getMyY() {
        return myY;
    }

    public DoubleProperty myYProperty() {
        return myY;
    }

    public void setMyY(double myY) {
        this.myY.set(myY);
    }

    public DoubleProperty getMyWidth() {
        return myWidth;
    }

    public DoubleProperty myWidthProperty() {
        return myWidth;
    }

    public void setMyWidth(double myWidth) {
        this.myWidth.set(myWidth);
    }
    public void setMyWidthProperty(DoubleProperty width){
    	myWidth = width;
    }

    public DoubleProperty getMyHeight() {
        return myHeight;
    }

    public DoubleProperty myHeightProperty() {
        return myHeight;
    }

    public void setMyHeight(double myHeight) {
        this.myHeight.set(myHeight);
    }
    public void setMyHeightProperty(DoubleProperty height){
    	myHeight = height;
    }

	public void setMyYProperty(double y) {
		myY.set(y);

	}


	public boolean isUserControlled() {
		return myUserControlled.getValue();
	}

	public void setUserControlled(boolean userControlled) {
		this.myUserControlled.setValue(userControlled);
	}

	public BooleanProperty getUserControlled() {
		return myUserControlled;
	}

	public void setUserControlled(BooleanProperty userControlled) {
		this.myUserControlled = userControlled;
	}

	public DoubleProperty getMyXLeftLimit() {
		return myLeftLimit;
	}

	public void setMyXLeftLimit(DoubleProperty myXLeftLimit) {
		this.myLeftLimit = myXLeftLimit;
	}

	public DoubleProperty getMyYDownLimit() {
		return myDownLimit;
	}

	public void setMyYDownLimit(DoubleProperty myYDownLimit) {
		this.myDownLimit = myYDownLimit;
	}

	public DoubleProperty getMyXRightLimit() {
		return myRightLimit;
	}

	public void setMyRightLimit(DoubleProperty myRightLimit) {
		this.myRightLimit = myRightLimit;
	}

	public DoubleProperty getMyYUpLimit() {
		return myUpLimit;
	}

	public void setMyYUpLimit(DoubleProperty myYUpLimit) {
		this.myUpLimit = myYUpLimit;
	}
/*
	public spriteState getState() {
		return state;
	}

	public void setState(spriteState state) {
		this.state = state;
	}

*/

	public DoubleProperty getMyRelativeX() {
		return myRelativeX;
	}

	public void setMyRelativeX(DoubleProperty myRelativeX) {
		this.myRelativeX = myRelativeX;
	}

	public DoubleProperty getMyRelativeY() {
		return myRelativeY;
	}

	public void setMyRelativeY(DoubleProperty myRelativeY) {
		this.myRelativeY = myRelativeY;
	}	



}
