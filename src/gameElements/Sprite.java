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
import level.LevelProperties;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Anything that is on the screen is described by this class. Has
 * behaviors(Movement, Attack, Defense, etc.), health, collision attributes,
 * properties, and a boolean that determines whether or not it is controlled by
 * the user.
 */

public class Sprite implements ISprite{

	private ISpriteProperties myProperties;
	private Health myHealth;
	private ListProperty<Collision> myCollisions;
	private ListProperty<Behavior> myBehaviors;
	private SpawnConditions spawnConditions;

	private RefObject myRef;

	public Sprite() {
		this("");
	}

	public Sprite(String ref) {
		this(new RefObject(ref));
	}

	public Sprite(RefObject myRef) {
		this.myRef = myRef;
		this.spawnConditions = new SpawnConditions();
		myProperties = new SpriteProperties();
		ObservableList<Collision> myCollisionList = FXCollections.observableList(new ArrayList<Collision>());
		myCollisions = new SimpleListProperty<Collision>(myCollisionList);
		
		ObservableList<Behavior> myBehaviorsList = FXCollections.observableList(new ArrayList<Behavior>());
		myBehaviors = new SimpleListProperty<Behavior>(myBehaviorsList);

		myHealth = new Health(100);

		myCollisions.add(new EnemyCollision());
	}
	
	public Sprite(ISpriteProperties myProperties, Health myHealth, List<Collision> myCollisions, RefObject myRef){

		this(myProperties, myHealth, myCollisions, new ArrayList<Behavior>(), myRef);
	}
	
	public Sprite(ISpriteProperties myProperties, Health myHealth, List<Collision> myCollisions,
			List<Behavior> myBehaviors, RefObject myRef) {
		this(myRef);
	
		ObservableList<Collision> ol = FXCollections.observableArrayList(myCollisions);
	
		ObservableList<Behavior> behaviorList=FXCollections.observableList(myBehaviors);
		this.myCollisions.set(ol);
		this.myProperties = myProperties;
		this.myHealth = myHealth;
		this.myBehaviors.set(behaviorList);
	}

	public Sprite(ISpriteProperties properties, Health health, ListProperty<Collision> myCollisions,
			ListProperty<Behavior> behaviors, RefObject myRef) {
		this(myRef);
		this.myProperties = properties;
		this.myHealth = health;
		this.myBehaviors.set(behaviors);
	//	this.behaviors.set(behaviors);
	}


	/**
	 * Updates the sprite frame by frame
	 */
	public void update(IActions actions, LevelProperties levProps) {
		actions.setSprite(this);
		//this.getSpriteProperties().updatePos();
 		for (Behavior behavior : this.getBehaviors()) {
			if(behavior.isReady(this)){
				behavior.execute(actions, levProps);
				behavior.stop(actions, levProps);
			}
		}
	}

	public Sprite getClone(){
		return new Sprite(this.myProperties.getClone(), this.myHealth.getClone(), this.myCollisions, this.myRef);
	}
	

	public void addBehavior(Behavior behavior) {
		this.getBehaviors().add(behavior);
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
	
	public void takeDamage(double damage) {
		//Damage defense before health
		for(Behavior behavior : this.getBehaviors()){
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

	public void setBehaviors(ListProperty<Behavior> behaviors) {
		this.myBehaviors = behaviors;
	}

	@Override
	public ListProperty<Behavior> getBehaviors() {
		return this.myBehaviors;
	}

	public SpawnConditions getSpawnConditions() {
		return spawnConditions;
	}

	public void setSpawnConditions(SpawnConditions spawnConditions) {
		this.spawnConditions = spawnConditions;
	}
}