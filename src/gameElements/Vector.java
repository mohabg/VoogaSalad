package gameElements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vector {

	private DoubleProperty xVel;
	private DoubleProperty yVel;

	public Vector() {
        xVel = new SimpleDoubleProperty();
        yVel = new SimpleDoubleProperty();
        xVel.set(0);
		yVel.set(0);
	}

	public Vector(double x, double y) {
		set(x, y);
	}
	public double getxVel() {
		return xVel.doubleValue();
	}

	public void setxVel(double xVel) {
		this.xVel.set(xVel);
	}

	public double getyVel() {
		return yVel.doubleValue();
	}

	public void setyVel(double yVel) {
		this.yVel.set(yVel);
	}

	public void set(double x, double y){
		xVel.set(x);
		yVel.set(y);
	}



}
