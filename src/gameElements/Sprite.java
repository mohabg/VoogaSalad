package gameElements;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;

public class Sprite {

//	private DoubleProperty x;
//	private IntegerProperty y;
//	private BooleanProperty width;
//	private DoubleProperty height;
//	private DoubleProperty angle;
	private SpriteProperties myProperties;
	private Health myHealth;
	private List<Collision> myCollisions;
	private Map<String, Behavior> myBehaviors;
	private RefObject myRef;
	private BooleanProperty isUserControlled;
	private BooleanProperty canMove;
	
	
	public Sprite(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions, Map<String, Behavior> myBehaviors, RefObject myRef) {
		super();
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		this.myCollisions = myCollisions;
		this.myBehaviors = myBehaviors;
		this.myRef = myRef;
		this.isUserControlled = new SimpleBooleanProperty(false);
		this.canMove = new SimpleBooleanProperty(true);
	}

	public Sprite(String ref) {
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
		return myProperties.getMyWidth();
	}

	public void setWidth(DoubleProperty width) {
		myProperties.setMyWidthProperty( width);
	}


	public DoubleProperty getHeight() {
		return myProperties.getMyHeight();
	}

	public void setHeight(DoubleProperty height) {
		myProperties.setMyHeightProperty(height);
	}


	public DoubleProperty getX() {
		return myProperties.getMyX();
	}

	public void setCoord(DoubleProperty x, DoubleProperty y) {
		myProperties.setMyXProperty(x);
		myProperties.setMyYProperty(y);
	}

	public void setX(DoubleProperty x) {
		myProperties.setMyXProperty(x);
	}

	public DoubleProperty getY() {
		return myProperties.getMyY();
	}

	public void setY(DoubleProperty y) {
		myProperties.setMyYProperty(y);
	}

	public DoubleProperty getAngle() {
		return myProperties.myAngleProperty();
	}

	public double getDistance(Sprite otherVect) {
		return Math.sqrt((Math.pow(myProperties.getMyX().doubleValue(), 2) - Math.pow(otherVect.getX().doubleValue(), 2)) + (Math.pow(myProperties.getMyY().doubleValue(), 2) - Math.pow(otherVect.getY().doubleValue(), 2)));
	}

	public void setAngle(DoubleProperty angle) {
		myProperties.setMyAngleProperty(angle);;
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
	
	public void setMySpriteProperties(SpriteProperties sp){
		myProperties = sp;
	}
	
	public SpriteProperties getSpriteProperties(){
		return myProperties;
	}
	
	public boolean isUserControlled(){
		return isUserControlled.getValue();
	}
	public void setAsUserControlled(){
		isUserControlled.set(true);
		Collision actorCollision = new ActorCollision();
		this.addCollision(actorCollision);
		setUserControlledBehaviors();
	}
	private void setUserControlledBehaviors() {
		//Should not create infinite loop because a behavior that is also a sprite does not have behaviors
		for(Behavior behavior : this.userBehaviors.values()){
			Class behaviorClass = behavior.getClass();
			try{
				Method method = behaviorClass.getMethod("setAsUserControlled", null);
				method.invoke(behavior, null);
			}
			catch(Exception e){
				
			}
		}
		
	}

	public boolean canMove() {
		return canMove.getValue();
	}
	public void disableMovement(){
		canMove.set(false);
	}
	public void enableMovement(){
		canMove.set(true);
	}
}