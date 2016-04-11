package gameElements;

import java.util.List;
import java.util.Map;

public class Sprite {

private double x;
private double y;
private double width;
private double height;
private double angle;
private Health myHealth;
private List<Collision> myCollisions;


	public Sprite() {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
	}
	public Sprite(double x, double y){
		this.x = x;
		this.y = y;
	}
	public Sprite(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		
	}

	public boolean isDead(){
		return myHealth.isDead();
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
	public Health getHealth() {
		return myHealth;
	}
	public void setHealth(Health myHealth) {
		this.myHealth = myHealth;
	}
	public void addCollision(Collision collision){
		myCollisions.add(collision);
	}
	public List<Collision> getCollisions(){
		return myCollisions;
	}
}