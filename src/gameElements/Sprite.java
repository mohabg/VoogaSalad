package gameElements;

import authoringEnvironment.RefObject;
import behaviors.Behavior;
import behaviors.MoveHorizontally;
import behaviors.MoveVertically;
import collisions.ActorCollision;
import collisions.Collision;
import collisions.DamageCollision;
import collisions.EnemyCollision;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
	private ListProperty<Collision> myCollisions;
	private MapProperty<StringProperty, Behavior> myBehaviors;
	private MapProperty<KeyCode, Behavior> userPressBehaviors;
	private MapProperty<KeyCode, Behavior> userReleaseBehaviors;
	
	// not observable
	private List<Collision> myCollisionsNoob;
	private Map<StringProperty, Behavior> myBehaviorsNoob;
	private Map<KeyCode, Behavior> userPressBehaviorsNoob;
	private Map<KeyCode, Behavior> userReleaseBehaviorsNoob;
	
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
		myCollisionsNoob = new ArrayList<Collision>();
		myBehaviorsNoob = new HashMap<StringProperty, Behavior>();
		userPressBehaviorsNoob = new HashMap<KeyCode, Behavior>();
		userReleaseBehaviorsNoob = new HashMap<KeyCode, Behavior>();
		
		ObservableList<Collision> ol = FXCollections.observableList(myCollisionsNoob);
		myCollisions = new SimpleListProperty<Collision>(ol);
		
		ObservableMap<StringProperty, Behavior> om1 = FXCollections.observableMap(myBehaviorsNoob);
		myBehaviors = new SimpleMapProperty<StringProperty, Behavior>(om1);
		
		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(userPressBehaviorsNoob);
		userPressBehaviors = new SimpleMapProperty<KeyCode, Behavior>(om2);
		
		ObservableMap<KeyCode, Behavior> om3 = FXCollections.observableMap(userReleaseBehaviorsNoob);
		userReleaseBehaviors = new SimpleMapProperty<KeyCode, Behavior>(om3);
		
		isUserControlled = new SimpleBooleanProperty(false);
		canMove = new SimpleBooleanProperty(true);
		myHealth = new Health(100);

		myCollisions.add(new DamageCollision(this,100));
		myCollisions.add(new EnemyCollision(this));

		Behavior defaultUpPressMovement = new MoveVertically(-5);
		myBehaviors.put(new SimpleStringProperty(defaultUpPressMovement.getClass().getName()), defaultUpPressMovement);
		userPressBehaviors.put(KeyCode.UP, defaultUpPressMovement);

		Behavior defaultDownPressMovement = new MoveVertically(5);
		userPressBehaviors.put(KeyCode.DOWN, defaultDownPressMovement);
		myBehaviors.put(new SimpleStringProperty(defaultDownPressMovement.getClass().getName()), defaultDownPressMovement);

		Behavior defaultVertReleaseMovement = new MoveVertically(0);
		userReleaseBehaviors.put(KeyCode.UP, defaultVertReleaseMovement);
		userReleaseBehaviors.put(KeyCode.DOWN, defaultVertReleaseMovement);
		myBehaviors.put(new SimpleStringProperty(defaultVertReleaseMovement.getClass().getName()), defaultVertReleaseMovement);

		Behavior defaultLeftPressMovement = new MoveHorizontally(-5);
		userPressBehaviors.put(KeyCode.LEFT, defaultLeftPressMovement);
		myBehaviors.put(new SimpleStringProperty(defaultLeftPressMovement.getClass().getName()), defaultLeftPressMovement);

		Behavior defaultRightPressMovement = new MoveHorizontally(5);
		userPressBehaviors.put(KeyCode.RIGHT, defaultRightPressMovement);
		myBehaviors.put(new SimpleStringProperty(defaultRightPressMovement.getClass().getName()), defaultRightPressMovement);

		Behavior defaultHorizReleaseMovement = new MoveHorizontally(0);
		userReleaseBehaviors.put(KeyCode.LEFT, defaultHorizReleaseMovement);
		userReleaseBehaviors.put(KeyCode.RIGHT, defaultHorizReleaseMovement);
		myBehaviors.put(new SimpleStringProperty(defaultHorizReleaseMovement.getClass().getName()), defaultHorizReleaseMovement);
	}

	public Sprite(SpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		this(myRef);
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		
		ObservableList<Collision> ol = FXCollections.observableArrayList(myCollisions);
		this.myCollisions.set(ol);
		
		Map<StringProperty, Behavior> testMap = new HashMap<StringProperty, Behavior>();
		for(String key : myBehaviors.keySet()) {
			testMap.put(new SimpleStringProperty(key), myBehaviors.get(key));
		}		
		ObservableMap<StringProperty, Behavior> om2 = FXCollections.observableMap(testMap);
		this.myBehaviors.set(om2);
		
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
		return userPressBehaviorsNoob;
		//return userPressBehaviors;
	}

	public void setUserPressBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(userBehaviors);
		this.userPressBehaviors.set(om2);
	}

	public void addUserPressBehavior(KeyCode key, Behavior behavior) {
		this.userPressBehaviors.put(key, behavior);
	}

	public Map<KeyCode, Behavior> getUserReleaseBehaviors() {
		return userReleaseBehaviorsNoob;
		//return userReleaseBehaviors.getValue();
	}

	public void setUserReleaseBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(userBehaviors);
		this.userReleaseBehaviors.set(om2);
	}

	public void addUserReleaseBehavior(KeyCode key, Behavior behavior) {
		this.userReleaseBehaviors.put(key, behavior);
	}

	public Map<String, Behavior> getBehaviors(){
		Map<String, Behavior> fakeB = new HashMap<String, Behavior>();
		for (StringProperty s : myBehaviorsNoob.keySet()) {
			fakeB.put(s.getValue(), myBehaviorsNoob.get(s));
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
		return myCollisionsNoob;
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