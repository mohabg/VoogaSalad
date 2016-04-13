package gameElements;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;

public class Sprite {

	private DoubleProperty x;
	private DoubleProperty y;
	private DoubleProperty width;
	private DoubleProperty height;
	private DoubleProperty angle;
	private Health myHealth;
	private List<Collision> myCollisions;
	private Map<String, Behavior> myBehaviors;
	private RefObject myRef;

	public Sprite() {
		myHealth = new Health();
		myCollisions = new ArrayList<Collision>();
	}

	public Sprite(String ref) {
		this();
		myRef = new RefObject(ref);
		DoubleProperty property=new SimpleDoubleProperty(0.0);
	
		setX(property);
		setY(property);
		setAngle(property);
		// myRef.setMyRef(ref);
	}


	public String getMyRef() {
		return myRef.getMyRef();
	}
	public void setMyRef(RefObject myRef) {
		this.myRef = myRef;
	}

	public Health getMyHealth() {
		return myHealth;
	}

	public void setMyHealth(Health myHealth) {
		this.myHealth = myHealth;
	}

	public List<Collision> getMyCollisions() {
		return myCollisions;
	}

	public void setMyCollisions(List<Collision> myCollisions) {
		this.myCollisions = myCollisions;
	}


	public boolean isDead() {
		return myHealth.isDead();
	}

	public DoubleProperty getWidth() {
		return width;
	}

	public void setWidth(DoubleProperty width) {
		this.width = width;
	}


	public DoubleProperty getHeight() {
		return height;
	}

	public void setHeight(DoubleProperty height) {
		this.height = height;
	}


	public DoubleProperty getX() {
		return x;
	}

	public void setCoord(DoubleProperty x, DoubleProperty y) {
		setX(x);
		setY(y);
	}

	public void setX(DoubleProperty x) {
		this.x = x;
	}

	public DoubleProperty getY() {
		return y;
	}

	public void setY(DoubleProperty y) {
		this.y = y;
	}

	public DoubleProperty getAngle() {
		return angle;
	}

	public double getDistance(Sprite otherVect) {
		return Math.sqrt((Math.pow(x.doubleValue(), 2) - Math.pow(otherVect.getX().doubleValue(), 2)) + (Math.pow(y.doubleValue(), 2) - Math.pow(otherVect.getY().doubleValue(), 2)));
	}

	public void setAngle(DoubleProperty angle) {
		this.angle = angle;
	}

	public Health getHealth() {
		return myHealth;
	}

	public void setHealth(Health myHealth) {
		this.myHealth = myHealth;
	}

	public void addCollision(Collision collision) {
		myCollisions.add(collision);
	}

	public List<Collision> getCollisions() {
		return myCollisions;
	}
}