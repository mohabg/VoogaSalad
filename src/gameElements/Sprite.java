package gameElements;

import authoringEnvironment.RefObject;
import behaviors.*;
import collisions.ActorCollision;
import collisions.Collision;
import collisions.EnemyCollision;
import gameplayer.SpriteFactory;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Anything that is on the screen is described by this class. Has
 * behaviors(Movement, Attack, Defense, etc.), health, collision attributes,
 * properties, and a boolean that determines whether or not it is controlled by
 * the user.
 */

public class Sprite {

	private SpriteProperties myProperties;
	private Health myHealth;

	private ListProperty<Collision> myCollisions;

	private MapProperty<StringProperty, Behavior> behaviors;
	private MapProperty<KeyCode, Behavior> userPressBehaviors;

	private RefObject myRef;
	private BooleanProperty canMove;

	public Sprite() {
		this("");
	}

	public Sprite(String ref) {
		this(new RefObject(ref));
	}

	public Sprite(RefObject myRef) {
		this.myRef = myRef;
		myProperties = new SpriteProperties();
		ObservableList<Collision> ol = FXCollections.observableList(new ArrayList<Collision>());
		myCollisions = new SimpleListProperty<Collision>(ol);

		ObservableMap<StringProperty, Behavior> om1 = FXCollections
				.observableMap(new HashMap<StringProperty, Behavior>());
		behaviors = new SimpleMapProperty<StringProperty, Behavior>(om1);

		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(new HashMap<KeyCode, Behavior>());
		userPressBehaviors = new SimpleMapProperty<KeyCode, Behavior>(om2);

		ObservableMap<KeyCode, Behavior> om3 = FXCollections.observableMap(new HashMap<KeyCode, Behavior>());

		canMove = new SimpleBooleanProperty(true);
		myHealth = new Health(100);

		myCollisions.add(new EnemyCollision());

		Attack gun = new Gun();
		addBehavior(gun);
		userPressBehaviors.put(KeyCode.SPACE, gun);
		gun.getBehaviorConditions().setProbability(0.5);
		
		Defense shield = new Shield();
		addBehavior(shield);
		userPressBehaviors.put(KeyCode.SHIFT, shield);

		Behavior thrustForward = new Thrust(-5);
		addBehavior(thrustForward);
		userPressBehaviors.put(KeyCode.UP, thrustForward);

		Behavior thrustReverse = new Thrust(5);
		userPressBehaviors.put(KeyCode.DOWN, thrustReverse);
		addBehavior(thrustReverse);
	
		/*
		 * Behavior defaultVertReleaseMovement = new MoveVertically(0);
		 * userReleaseBehaviors.put(KeyCode.UP, defaultVertReleaseMovement);
		 * userReleaseBehaviors.put(KeyCode.DOWN, defaultVertReleaseMovement);
		 * addBehavior(defaultVertReleaseMovement);
		 */

		Behavior rotateLeft = new MoveTurn(-1);
		userPressBehaviors.put(KeyCode.LEFT, rotateLeft);
		addBehavior(rotateLeft);

		Behavior rotateRight = new MoveTurn(1);
		userPressBehaviors.put(KeyCode.RIGHT, rotateRight);
		addBehavior(rotateRight);

		/*
		 * Behavior defaultHorizReleaseMovement = new MoveHorizontally(0);
		 * userReleaseBehaviors.put(KeyCode.LEFT, defaultHorizReleaseMovement);
		 * userReleaseBehaviors.put(KeyCode.RIGHT, defaultHorizReleaseMovement);
		 * addBehavior(defaultHorizReleaseMovement);
		 */

	}

	public Sprite(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		this(myRef);
		
		ObservableList<Collision> ol = FXCollections.observableArrayList(myCollisions);
		Map<StringProperty, Behavior> testMap = changeKeysToProperties(myBehaviors);		
		ObservableMap<StringProperty, Behavior> om2 = FXCollections.observableMap(testMap);
		
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		this.behaviors.set(om2);
		this.canMove = new SimpleBooleanProperty(true);
	}

	public Sprite(SpriteProperties properties, Health health, ListProperty<Collision> myCollisions,
			MapProperty<StringProperty, Behavior> behaviors, RefObject myRef) {
		this(myRef);
		this.myProperties = properties;
		this.myHealth = health;
		this.behaviors.set(behaviors);
		this.canMove = new SimpleBooleanProperty(true);
	}

	/**
	 * Updates the sprite frame by frame
	 */
	public void update(SpriteFactory spriteFactory) {
		this.getSpriteProperties().updatePos();
		for (Behavior behavior : behaviors.values()) {
			if(behavior.isReady(this)){
				behavior.apply(this, spriteFactory);
			}
		}
	}
	
	public void thrustSprite(DoubleProperty intensity) {
		double currentXVelocity = getSpriteProperties().getMyXvel().doubleValue();
		double currentYVelocity = getSpriteProperties().getMyYvel().doubleValue();
		this.getSpriteProperties().setMyXvel(currentXVelocity + Math.sin(this.getAngle().getValue()) * intensity.getValue());
		this.getSpriteProperties().setMyYvel(currentYVelocity + Math.cos(this.getAngle().getValue()) * intensity.getValue());

	}


	public Sprite getClone(){
		return new Sprite(this.myProperties.getClone(), this.myHealth.getClone(), this.myCollisions,
												this.behaviors, this.myRef);
	}
	
	public Map<KeyCode, Behavior> getUserPressBehaviors() {
		return userPressBehaviors;
	}

	public void addBehavior(Behavior behavior) {
		behaviors.put(new SimpleStringProperty(behavior.getClass().getName()), behavior);

	}

	public void setUserPressBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(userBehaviors);
		this.userPressBehaviors.set(om2);
	}

	public void addUserPressBehavior(KeyCode key, Behavior behavior) {
		this.userPressBehaviors.put(key, behavior);
	}

	public Map<String, Behavior> getBehaviors(){
		Map<String, Behavior> fakeB = new HashMap<String, Behavior>();
		for (StringProperty s : behaviors.keySet()) {
			fakeB.put(s.getValue(), behaviors.get(s));
		}

		return fakeB;
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

	public void setMyCollisions(List<Collision> myCollisions) {
		ObservableList<Collision> ol = FXCollections.observableArrayList(myCollisions);
		this.myCollisions.set(ol);
	}

	public void kill() {
		myHealth.kill();
	}

	public boolean isDead() {
		return myHealth.isDead();
	}

	public DoubleProperty getWidth() {
		return myProperties.getMyWidth();
	}

	public void setWidth(DoubleProperty width) {
		myProperties.setMyWidthProperty(width);
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

	public StringProperty getMyStringRef() {
		return myRef.getMyStringRef();
	}

	public ObjectProperty<Image> getMyImageProp() {
		return new SimpleObjectProperty<Image>(new Image(myRef.getMyStringRef().getValue()));
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

	public void setY(double y) {
		myProperties.setMyYProperty(y);
	}

	public void setY(DoubleProperty y) {
		myProperties.setMyYProperty(y);
	}

	public DoubleProperty getAngle() {
		return myProperties.getMyAngleProperty();
	}

	public double getDistance(Sprite otherVect) {
		return Math.sqrt((Math.pow(myProperties.getMyX().doubleValue(), 2)
				- Math.pow(otherVect.getX().doubleValue(), 2))
				+ (Math.pow(myProperties.getMyY().doubleValue(), 2) - Math.pow(otherVect.getY().doubleValue(), 2)));
	}

	public void setAngle(DoubleProperty angle) {
		myProperties.setMyAngleProperty(angle);
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

	public ListProperty<Collision> getCollisions() {
		return myCollisions;
	}

	public void setMySpriteProperties(SpriteProperties sp) {
		myProperties = sp;
	}

	public SpriteProperties getSpriteProperties() {
		return myProperties;
	}

	public boolean isUserControlled() {

		return myProperties.isUserControlled();
	}

	/**
	 * Sets this sprite as being controlled by the user
	 * @param bool TODO
	 */
	public void setUserControlled(boolean bool) {
		myProperties.setUserControlled(bool);
		if(bool == true){
			removeCollisionType(new EnemyCollision());
			addCollision(new ActorCollision());
		} 
		else{
			removeCollisionType(new ActorCollision());
			addCollision(new EnemyCollision());
		}
	}
	public boolean canMove() {
		return canMove.getValue();
	}

	public void disableMovement() {
		canMove.set(false);
	}

	public void enableMovement() {
		canMove.set(true);
	}

	/**
	 *
	 * @param keyCode
	 *            Checks if the KeyCode corresponds to an action
	 * @return
	 */
	public Behavior getUserPressBehavior(KeyCode keyCode) {
		return userPressBehaviors.get(keyCode);
	}
	
	public void takeDamage(double damage) {
		//Damage defense before health
		for(Behavior behavior : this.getBehaviors().values()){
			if(behavior instanceof Defense){
				Defense defense = (Defense) behavior;
				if(defense.isEnabled()){
					defense.takeDamage(damage);
					if(defense.getHealth().isDead()){
						return;
					}
				}
			}
		}
		this.getHealth().takeDamage(damage);
	}

	private void removeCollisionType(Collision col){
		Iterator<Collision> it = getCollisions().iterator();
		while(it.hasNext()){
			Collision nextCol = it.next();
			if(nextCol.getClass().getName().equals(col.getClass().getName())){
				it.remove();
			}
		}
	}
	private Map<StringProperty, Behavior> changeKeysToProperties(Map<String, Behavior> myBehaviors) {
		Map<StringProperty, Behavior> testMap = new HashMap<StringProperty, Behavior>();
		for(String key : myBehaviors.keySet()) {
			testMap.put(new SimpleStringProperty(key), myBehaviors.get(key));
		}
		return testMap;
	}
}