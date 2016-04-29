package gameElements;

import authoringEnvironment.RefObject;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.SetFieldName;
import behaviors.*;
import collisions.ActorCollision;
import collisions.Collision;
import collisions.EnemyCollision;
import events.Executable;
import gameElements.ISprite.spriteState;
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

public class Sprite implements ISprite, IEnemy{

	private ISpriteProperties myProperties;
	private Health myHealth;
	private ListProperty<Collision> myCollisions;
	private MapProperty<StringProperty, Behavior> behaviors;
	private MapProperty<KeyCode, Executable> userPressBehaviors;

	private RefObject myRef;

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
		behaviors.sizeProperty().addListener((o, ov, nv) -> {
			System.out.println("poo");
		});
		ObservableMap<KeyCode, Executable> om2 = FXCollections.observableMap(new HashMap<KeyCode, Executable>());
		userPressBehaviors = new SimpleMapProperty<KeyCode, Executable>(om2);

		ObservableMap<KeyCode, Behavior> om3 = FXCollections.observableMap(new HashMap<KeyCode, Behavior>());
		
		myHealth = new Health(100);

		myCollisions.add(new EnemyCollision());

		Attack gun = new Gun();
		addBehavior(gun);
		userPressBehaviors.put(KeyCode.SPACE, gun);
		gun.getBehaviorConditions().setProbability(0.5);
		
		Defense shield = new Shield();
		addBehavior(shield);
		userPressBehaviors.put(KeyCode.SHIFT, shield);


		Behavior thrustForward = new ThrustVertical(-1);
		addBehavior(thrustForward);
		userPressBehaviors.put(KeyCode.UP, thrustForward);

		Behavior thrustReverse = new ThrustVertical(1);
		userPressBehaviors.put(KeyCode.DOWN, thrustReverse);
		addBehavior(thrustReverse);
		
		Behavior moveLeft = new ThrustHorizontal(-1);
		userPressBehaviors.put(KeyCode.LEFT, moveLeft);
		addBehavior(moveLeft);

		Behavior moveRight = new ThrustHorizontal(1);
		userPressBehaviors.put(KeyCode.RIGHT, moveRight);
		addBehavior(moveRight);
		
		Behavior moveTurnRight = new MoveTurn(2);
		userPressBehaviors.put(KeyCode.A, moveTurnRight);
		addBehavior(moveTurnRight);
		
		Behavior moveTurnLeft = new MoveTurn(358);
		userPressBehaviors.put(KeyCode.D, moveTurnLeft);
		addBehavior(moveTurnLeft);

	}
	
	public Sprite(ISpriteProperties myProperties, Health myHealth, List<Collision> myCollisions, RefObject myRef){
		this(myProperties, myHealth, myCollisions, new HashMap<String, Behavior>(), myRef);
	}
	
	public Sprite(ISpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			Map<String, Behavior> myBehaviors, RefObject myRef) {
		this(myRef);
	
		ObservableList<Collision> ol = FXCollections.observableArrayList(myCollisions);
		Map<StringProperty, Behavior> testMap = changeKeysToProperties(myBehaviors);		
		ObservableMap<StringProperty, Behavior> om2 = FXCollections.observableMap(testMap);
		
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		this.behaviors.set(om2);
	}

	public Sprite(SpriteProperties properties, Health health, ListProperty<Collision> myCollisions,
			MapProperty<StringProperty, Behavior> behaviors, RefObject myRef) {
		this(myRef);
		this.myProperties = properties;
		this.myHealth = health;
		this.behaviors.set(behaviors);
	}

	/**
	 * Updates the sprite frame by frame
	 */
	public void update(IActions actions) {
		this.getSpriteProperties().updatePos();
		for (Behavior behavior : behaviors.values()) {
			if(behavior.isReady(this)){
				behavior.apply(actions, null);
			}
		}
	}

	public Sprite getClone(){
		return new Sprite(this.myProperties.getClone(), this.myHealth.getClone(), this.myCollisions, this.myRef);
	}
	
	public MapProperty<KeyCode, Executable> getUserPressBehaviors() {
		return userPressBehaviors;
	}

	public void addBehavior(Behavior behavior) {
		behaviors.put(new SimpleStringProperty(behavior.getClass().getName()), behavior);

	}

	public void setUserPressBehaviors(Map<KeyCode, Executable> userBehaviors) {
		ObservableMap<KeyCode, Executable> om2 = FXCollections.observableMap(userBehaviors);
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

	@Override
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

	public StringProperty getMyStringRef() {
		return myRef.getMyStringRef();
	}

	public ObjectProperty<Image> getMyImageProp() {
		return new SimpleObjectProperty<Image>(new Image(myRef.getMyStringRef().getValue()));
	}
	
	public double getDistance(ISprite otherVect) {
		return Math.sqrt((Math.pow(myProperties.getX(), 2)
				- Math.pow(otherVect.getSpriteProperties().getX(), 2))
				+ (Math.pow(myProperties.getY(), 2) - Math.pow(otherVect.getSpriteProperties().getY(), 2)));
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

	public void setMySpriteProperties(ISpriteProperties sp) {
		myProperties = sp;
	}

	public ISpriteProperties getSpriteProperties() {
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
	/**
	 *
	 * @param keyCode
	 *            Checks if the KeyCode corresponds to an action
	 * @return
	 */
	public Executable getUserPressBehavior(KeyCode keyCode) {
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
	
	public boolean isOutOfBounds() {
		return this.getSpriteProperties().isOutOfBounds();
	}

	@Override
	public IEnemy clone() {
		ISpriteProperties clonedProperties = this.getSpriteProperties().getClone();
		List<Collision> clonedCollisions = new ArrayList<>();
		for(Collision col : this.getCollisions()){
			clonedCollisions.add(col.clone());
		}
		IEnemy clone = new Sprite(clonedProperties, this.myHealth.getClone(), clonedCollisions, new RefObject(this.getMyRef()));
		return clone;
	}

	@Override
	public double getSpawnProbability() {
		// TODO Auto-generated method stub
		return 0;
	}	

	public void spawn() {
		if (Math.random() < getSpawnProbability())
			clone();
	}
}