package gameElements;

import authoringEnvironment.RefObject;
import behaviors.Attack;
import behaviors.Behavior;
import behaviors.Bullet;
import behaviors.MoveHorizontally;
import behaviors.MoveVertically;
import collisions.ActorCollision;
import collisions.Collision;
import collisions.DamageCollision;
import collisions.EnemyCollision;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;

/**
 * Anything that is on the screen is described by this class. Has
 * behaviors(Movement, Attack, Defense, etc.), health, collision attributes,
 * properties, and a boolean that determines whether or not it is controlled by
 * the user.
 */

public class Sprite {

	private SpriteProperties myProperties;
	private Health myHealth;
	private List<Collision> myCollisions;
	private Map<String, Behavior> myBehaviors;
	private Map<KeyCode, Behavior> userPressBehaviors;
	private Map<KeyCode, Behavior> userReleaseBehaviors;
	private RefObject myRef;
	private BooleanProperty isUserControlled;
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
		myCollisions = new ArrayList<Collision>();
		myBehaviors = new HashMap<String, Behavior>();
		userPressBehaviors = new HashMap<KeyCode, Behavior>();
		userReleaseBehaviors = new HashMap<KeyCode, Behavior>();
		isUserControlled = new SimpleBooleanProperty(false);
		canMove = new SimpleBooleanProperty(true);
		myHealth = new Health(100);

		myCollisions.add(new DamageCollision(this,100));
		myCollisions.add(new EnemyCollision(this));

		Attack bullet = new Bullet();
		userPressBehaviors.put(KeyCode.SPACE, bullet);
		
		Behavior defaultUpPressMovement = new MoveVertically(-5);
		myBehaviors.put(defaultUpPressMovement.getClass().getName(), defaultUpPressMovement);
		userPressBehaviors.put(KeyCode.UP, defaultUpPressMovement);

		Behavior defaultDownPressMovement = new MoveVertically(5);
		userPressBehaviors.put(KeyCode.DOWN, defaultDownPressMovement);
		myBehaviors.put(defaultDownPressMovement.getClass().getName(), defaultDownPressMovement);

		Behavior defaultVertReleaseMovement = new MoveVertically(0);
		userReleaseBehaviors.put(KeyCode.UP, defaultVertReleaseMovement);
		userReleaseBehaviors.put(KeyCode.DOWN, defaultVertReleaseMovement);
		myBehaviors.put(defaultVertReleaseMovement.getClass().getName(), defaultVertReleaseMovement);

		Behavior defaultLeftPressMovement = new MoveHorizontally(-5);
		userPressBehaviors.put(KeyCode.LEFT, defaultLeftPressMovement);
		myBehaviors.put(defaultLeftPressMovement.getClass().getName(), defaultLeftPressMovement);

		Behavior defaultRightPressMovement = new MoveHorizontally(5);
		userPressBehaviors.put(KeyCode.RIGHT, defaultRightPressMovement);
		myBehaviors.put(defaultRightPressMovement.getClass().getName(), defaultRightPressMovement);

		Behavior defaultHorizReleaseMovement = new MoveHorizontally(0);
		userReleaseBehaviors.put(KeyCode.LEFT, defaultHorizReleaseMovement);
		userReleaseBehaviors.put(KeyCode.RIGHT, defaultHorizReleaseMovement);
		myBehaviors.put(defaultHorizReleaseMovement.getClass().getName(), defaultHorizReleaseMovement);
	}

	public Sprite(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		this(myRef);
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		this.myCollisions = myCollisions;
		this.myBehaviors = myBehaviors;
		this.myRef = myRef;
		this.isUserControlled = new SimpleBooleanProperty(false);
		this.canMove = new SimpleBooleanProperty(true);
	}

	/**
	 * Updates the sprite frame by frame
	 */
	public void update() {
		myProperties.updatePos();
		for (Behavior behavior : myBehaviors.values()) {
//			 behavior.apply(this);
		}

	}

	public Map<KeyCode, Behavior> getUserPressBehaviors() {
		return userPressBehaviors;
	}

	public void setUserPressBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		this.userPressBehaviors = userBehaviors;
	}

	public void addUserPressBehavior(KeyCode key, Behavior behavior) {
		this.userPressBehaviors.put(key, behavior);
	}

	public Map<KeyCode, Behavior> getUserReleaseBehaviors() {
		return userReleaseBehaviors;
	}

	public void setUserReleaseBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		this.userReleaseBehaviors = userBehaviors;
	}

	public void addUserReleaseBehavior(KeyCode key, Behavior behavior) {
		this.userReleaseBehaviors.put(key, behavior);
	}

	public Map<String, Behavior> getBehaviors() {
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

	public DoubleProperty getAngle() {
		return myProperties.myAngleProperty();
	}

	public double getDistance(Sprite otherVect) {
		return Math.sqrt((Math.pow(myProperties.getMyX().doubleValue(), 2)
				- Math.pow(otherVect.getX().doubleValue(), 2))
				+ (Math.pow(myProperties.getMyY().doubleValue(), 2) - Math.pow(otherVect.getY().doubleValue(), 2)));
	}

	public void setAngle(DoubleProperty angle) {
		myProperties.setMyAngleProperty(angle);
		;
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

	public void setMySpriteProperties(SpriteProperties sp) {
		myProperties = sp;
	}

	public SpriteProperties getSpriteProperties() {
		return myProperties;
	}

	public boolean isUserControlled() {
		return isUserControlled.getValue();
	}

	/**
	 * Sets this sprite as being controlled by the user
	 */
	public void setAsUserControlled() {
		isUserControlled.set(true);
		setActorCollision();
		invokeMethodInBehaviors("setAsUserControlled", null, null);
	}

	private void setActorCollision() {
		// Remove enemy and add actor collision
		Collision actorCollision = new ActorCollision(this);
		Iterator<Collision> it = getCollisions().iterator();
		while (it.hasNext()) {
			Collision collision = it.next();
			if (collision instanceof EnemyCollision) {
				it.remove();
			}
		}
		this.addCollision(actorCollision);
	}

	public void invokeMethodInBehaviors(String methodName, Class[] parameters, Object[] objects) {
		for (Behavior behavior : userPressBehaviors.values()) {
			Class behaviorClass = behavior.getClass();
			try{
				Method method = behaviorClass.getDeclaredMethod(methodName, parameters);
				method.invoke(behavior, objects);
			}
			catch(Exception e){
				
			}
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

	public Behavior getUserReleaseBehavior(KeyCode keyCode) {
		return userReleaseBehaviors.get(keyCode);
	}

}