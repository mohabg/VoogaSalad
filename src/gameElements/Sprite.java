package gameElements;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Sprite extends Observable{

private double x;
private double y;
private double width;
private double height;
private double angle;
private Health myHealth;
private Collision myCollision;

	public Sprite() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
	}
	public Sprite(double x, double y, double xVel, double yVel){
		this.x = x;
		this.y = y;
	}
	public Sprite(double x, double y, double angle, double xVel, double yVel){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public Sprite(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public Sprite(double x, double y, double angle, double xVel, double yVel, Health healthVal){
		this.x = x;
		this.y = y;
		this.angle = angle;
		myHealth=healthVal;
		getHealth().addObserver((Observer) this);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Sprite(double x, double y){
		this(x, y, 0);
	}

	public double getX() {
		return x;
	}

	public void setCoord(double x, double y){
		setX(x);
		setY(y);
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public double getDistance(Sprite otherVect){
		return Math.sqrt(( Math.pow(x,2) - Math.pow(otherVect.getX(),2))
				+ (Math.pow(y,2) - Math.pow(otherVect.getY(), 2)));
	}


	public void setAngle(double angle) {
		this.angle = angle;
	}
	public Health getHealth(){
		return myHealth;
	}

	public void setCollision(Collision collision){
		myCollision = collision;
	}
	public Collision getCollision(){
		return myCollision;
	}
}