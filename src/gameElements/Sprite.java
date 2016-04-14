package gameElements;

import authoringEnvironment.RefObject;
import authoringEnvironment.SpriteProperties;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	private Map<KeyCode, Behavior> userBehaviors;
	private RefObject myRef;
	private boolean isUserControlled;
	private boolean canMove;
	
	public Sprite(RefObject myRef){
		this.myRef = myRef;
		myProperties = new SpriteProperties(0, 0, 10 ,10 ,0);
		myHealth = new Health(100);
		myCollisions = new ArrayList<Collision>();
		myCollisions.add(new EnemyCollision());
		myBehaviors = new HashMap<String, Behavior>();
		Behavior defaultMovement = new MoveVertically(5);
		myBehaviors.put(defaultMovement.getClass().getName(), defaultMovement);
		userBehaviors.put(KeyCode.DOWN, defaultMovement);
		userBehaviors.put(KeyCode.UP, defaultMovement);
	}
	public Sprite(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		super();
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		this.myCollisions = myCollisions;
		this.myBehaviors = myBehaviors;
		this.myRef = myRef;
		this.isUserControlled = false;
		canMove = true;
	}
	
	public void update(){
		for(Behavior behavior : myBehaviors.values()){
			behavior.apply(this);
		}
	}
	public Map<KeyCode, Behavior> getUserBehaviors() {
		return userBehaviors;
	}

	public void setUserBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		this.userBehaviors = userBehaviors;
	}
	public void addUserBehavior(KeyCode key, Behavior behavior){
		this.userBehaviors.put(key, behavior);
	}
	public Map<String, Behavior> getBehaviors(){
		return myBehaviors;
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

	public void setHealth(double myHealth) {
		this.myHealth.setHealth(myHealth);
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
		return isUserControlled;
	}
	public void setAsUserControlled(){
		isUserControlled = true;
		setActorCollision();
		setUserControlledBehaviors();
	}

	private void setActorCollision() {
		//Remove enemy and add actor collision
		Collision actorCollision = new ActorCollision();
		Iterator<Collision> it = getCollisions().iterator();
		while(it.hasNext()){
			Collision collision = it.next();
			if(collision instanceof EnemyCollision){
				it.remove();
			}
		}
		this.addCollision(actorCollision);
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
		return canMove;
	}
	public void disableMovement(){
		canMove = false;
	}
	public void enableMovement(){
		canMove = true;
	}
}