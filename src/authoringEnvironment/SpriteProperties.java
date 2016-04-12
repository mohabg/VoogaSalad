package authoringEnvironment;

import javafx.beans.property.DoubleProperty;

/**
 * Created by davidyan on 4/12/16.
 */
public class SpriteProperties {
    private DoubleProperty myX;
    private DoubleProperty myY;
    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private DoubleProperty myAngle;

    public SpriteProperties(){
        myX.set(0);
        myY.set(0);
        myWidth.set(0);
        myHeight.set(0);
        myAngle.set(0);

    }

    public SpriteProperties(double x, double y, double width, double height, double angle){
        myX.set(x);
        myY.set(y);
        myWidth.set(width);
        myHeight.set(height);
        myAngle.set(angle);
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

    public double getMyX() {
        return myX.get();
    }

    public DoubleProperty myXProperty() {
        return myX;
    }

    public void setMyX(double myX) {
        this.myX.set(myX);
    }

    public double getMyY() {
        return myY.get();
    }

    public DoubleProperty myYProperty() {
        return myY;
    }

    public void setMyY(double myY) {
        this.myY.set(myY);
    }

    public double getMyWidth() {
        return myWidth.get();
    }

    public DoubleProperty myWidthProperty() {
        return myWidth;
    }

    public void setMyWidth(double myWidth) {
        this.myWidth.set(myWidth);
    }

    public double getMyHeight() {
        return myHeight.get();
    }

    public DoubleProperty myHeightProperty() {
        return myHeight;
    }

    public void setMyHeight(double myHeight) {
        this.myHeight.set(myHeight);
    }



}
