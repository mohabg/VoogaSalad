package gameElements;

import authoringEnvironment.RefObject;
import behaviors.Attack;
import behaviors.Behavior;
import behaviors.Bullet;
import behaviors.MoveHorizontally;
import behaviors.MoveVertically;
import behaviors.SquarePattern;
import collisions.ActorCollision;
import collisions.Collision;
import collisions.DamageCollision;
import collisions.EnemyCollision;
import gameplayer.SpriteFactory;
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
	private MapProperty<StringProperty, Behavior> automaticBehaviors;
	private MapProperty<KeyCode, Behavior> userPressBehaviors;
	private MapProperty<KeyCode, Behavior> userReleaseBehaviors;
	
	// not observable
//	private List<Collision> myCollisionsNoob;
//	private Map<StringProperty, Behavior> automaticBehaviorsNoob;
//	private Map<KeyCode, Behavior> userPressBehaviorsNoob;
//	private Map<KeyCode, Behavior> userReleaseBehaviorsNoob;

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

//		myCollisionsNoob = new ArrayList<Collision>();
//		automaticBehaviorsNoob = new HashMap<StringProperty, Behavior>();
//		userPressBehaviorsNoob = new HashMap<KeyCode, Behavior>();
//		userReleaseBehaviorsNoob = new HashMap<KeyCode, Behavior>();
		
		ObservableList<Collision> ol = FXCollections.observableList(new ArrayList<Collision>());
		myCollisions = new SimpleListProperty<Collision>(ol);
		
		ObservableMap<StringProperty, Behavior> om1 = FXCollections.observableMap(new HashMap<StringProperty, Behavior>());
		automaticBehaviors = new SimpleMapProperty<StringProperty, Behavior>(om1);
		
		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(new HashMap<KeyCode, Behavior>());
		userPressBehaviors = new SimpleMapProperty<KeyCode, Behavior>(om2);
		
		ObservableMap<KeyCode, Behavior> om3 = FXCollections.observableMap(new HashMap<KeyCode, Behavior>());
		userReleaseBehaviors = new SimpleMapProperty<KeyCode, Behavior>(om3);
		
		

		canMove = new SimpleBooleanProperty(true);
		myHealth = new Health(100);

		//myCollisions.add(new EnemyCollision());

		Attack bullet = new Bullet();
		userPressBehaviors.put(KeyCode.SPACE, bullet);
		
		Behavior defaultUpPressMovement = new MoveVertically(-5);
		//automaticBehaviors.put(new SimpleStringProperty(defaultUpPressMovement.getClass().getName()), defaultUpPressMovement);
		userPressBehaviors.put(KeyCode.UP, defaultUpPressMovement);

		Behavior defaultDownPressMovement = new MoveVertically(5);
		userPressBehaviors.put(KeyCode.DOWN, defaultDownPressMovement);
		//automaticBehaviors.put(new SimpleStringProperty(defaultDownPressMovement.getClass().getName()), defaultDownPressMovement);


		Behavior defaultVertReleaseMovement = new MoveVertically(0);
		userReleaseBehaviors.put(KeyCode.UP, defaultVertReleaseMovement);
		userReleaseBehaviors.put(KeyCode.DOWN, defaultVertReleaseMovement);


		Behavior defaultLeftPressMovement = new MoveHorizontally(-5);
		userPressBehaviors.put(KeyCode.LEFT, defaultLeftPressMovement);

		Behavior defaultRightPressMovement = new MoveHorizontally(5);
		userPressBehaviors.put(KeyCode.RIGHT, defaultRightPressMovement);


		Behavior defaultHorizReleaseMovement = new MoveHorizontally(0);
		userReleaseBehaviors.put(KeyCode.LEFT, defaultHorizReleaseMovement);
		userReleaseBehaviors.put(KeyCode.RIGHT, defaultHorizReleaseMovement);


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
		this.automaticBehaviors.set(om2);
		this.myRef = myRef;
		this.canMove = new SimpleBooleanProperty(true);
	}

	/**
	 * Updates the sprite frame by frame
	 */
	public void update(SpriteFactory spriteFactory) {
		myProperties.updatePos();
		for (Behavior behavior : automaticBehaviors.values()) {
			 behavior.apply(this, spriteFactory);
		}

	}

	public Map<KeyCode, Behavior> getUserPressBehaviors() {
		return userPressBehaviors;
		//return userPressBehaviors;
	}

	public void addBehavior(String key, Behavior behavior){
		automaticBehaviors.put(new SimpleStringProperty(key), behavior);

	}

	public void setUserPressBehaviors(Map<KeyCode, Behavior> userBehaviors) {
		ObservableMap<KeyCode, Behavior> om2 = FXCollections.observableMap(userBehaviors);
		this.userPressBehaviors.set(om2);
	}

	public void addUserPressBehavior(KeyCode key, Behavior behavior) {
		this.userPressBehaviors.put(key, behavior);
	}

	public Map<KeyCode, Behavior> getUserReleaseBehaviors() {
		return userReleaseBehaviors;
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
		for (StringProperty s : automaticBehaviors.keySet()) {
			fakeB.put(s.getValue(), automaticBehaviors.get(s));
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
	public void kill(){
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
    }

	public Health getHealth() {
		return myHealth;
	}

	public void setHealth(Health myHealth) {
		this.myHealth = myHealth;
	}

	public void addCollision(Collision collision) {
		if (collision == null) {
			System.out.println("NULLLLLLL");
		} 
		if (myCollisions == null) {
			System.out.println("COLL NULL");
		}
	
		
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
	 */
	public void setAsUserControlled() {
		myProperties.setUserControlled(true);
		setActorCollision();
	}

	private void setActorCollision() {
		// Remove enemy and add actor collision
		Collision actorCollision = new ActorCollision();
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