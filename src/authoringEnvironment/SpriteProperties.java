package authoringEnvironment;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author David Yan, Joe Jacob, Huijia Yu
 */
public class SpriteProperties {
    private DoubleProperty myX;
    private DoubleProperty myY;
    private DoubleProperty myWidth;
    private DoubleProperty myHeight;
    private DoubleProperty myAngle;

    public SpriteProperties(){
        myX = new SimpleDoubleProperty();
        myY = new SimpleDoubleProperty();
        myWidth = new SimpleDoubleProperty();
        myHeight = new SimpleDoubleProperty();
        myAngle = new SimpleDoubleProperty();
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



}
