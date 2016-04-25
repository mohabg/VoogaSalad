package gameElements;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class SpriteProperties {
    private DoubleProperty myX;
    private DoubleProperty myY;
    private DoubleProperty myXvel;
    private DoubleProperty myYvel;
    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private DoubleProperty myAngle;
    private BooleanProperty myUserControlled;
    
    public SpriteProperties(){
        myX = new SimpleDoubleProperty(0);
        myY = new SimpleDoubleProperty(0);
        myXvel = new SimpleDoubleProperty(0);
        myYvel = new SimpleDoubleProperty(0);
        myWidth = new SimpleDoubleProperty(0);
        myHeight = new SimpleDoubleProperty(0);
        myAngle = new SimpleDoubleProperty(0);
        myUserControlled = new SimpleBooleanProperty(false);
//        myX.set(0);
//        myY.set(0);
//        myWidth.set(0);
//        myHeight.set(0);
//        myAngle.set(0);
    }

    public SpriteProperties(double x, double y, double width, double height, double angle){
    	this();
        myX.set(x);
        myY.set(y);
        myWidth.set(width);
        myHeight.set(height);
        myAngle.set(angle);
    }

    public SpriteProperties(double x, double y, double xVel, double yVel, double width, double height, double angle){
    	this();
        myX.set(x);
        myY.set(y);
        myXvel.set(xVel);
        myYvel.set(yVel);
        myWidth.set(width);
        myHeight.set(height);
        myAngle.set(angle);
    }

    public void updatePos(){
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

    public DoubleProperty myAngleProperty() {
        return myAngle;
    }

    public void setMyAngle(double myAngle) {
        this.myAngle.set(myAngle);
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
  //   	System.out.println("setting " + myY);
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



}
