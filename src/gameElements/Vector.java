package gameElements;

public class Vector {

	private double xVel;
	private double yVel;

	public Vector() {
		xVel = 0;
		yVel = 0;
	}

	public Vector(double x, double y) {
		set(x, y);
	}
	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public void set(double x, double y){
		xVel = x;
		yVel = y;
	}



}
