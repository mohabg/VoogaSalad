package behaviors;

import authoringEnvironment.RefObject;
import gameElements.ExecuteConditions;
import gameElements.Health;
import gameElements.Sprite;
import gameplayer.SpriteFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Deals with an array of behaviors having to do with how Sprites defend themselves. The enabled boolean lets one know if the 
 * sprite currently has this defense enabled. Behavior conditions refers to an array of properties that help determine if the 
 * Sprite is eligible for defending itself. It is only used for enemy sprites.
 * @see ExecuteConditions
 */


public abstract class Defense extends Behavior {

	private ExecuteConditions behaviorConditions;
	private RefObject myRef;
	private Health health;
	
	public Defense(){
		this(new RefObject());
	}
	
	public Defense(RefObject myRef){
		this(myRef, new ExecuteConditions(), new Health());
	}
	public Defense(RefObject myRef, Health myHealth){
		this(myRef, new ExecuteConditions(), new Health());
	}
	public Defense(RefObject myRef, ExecuteConditions behaviorConditions, Health myHealth){
		super();
		this.myRef = myRef;
		this.behaviorConditions = behaviorConditions;
		this.health = health;
	}
	public RefObject getMyRef() {
		return myRef;
	}

	public void setMyRef(RefObject myRef) {
		this.myRef = myRef;
	}
	
	public void takeDamage(double damage){
		this.health.takeDamage(damage);
	}
	public ExecuteConditions getBehaviorConditions(){
		return behaviorConditions;
	}
	
	/**
	 * @param sprite Enables the defense of a sprite, provided the readyToDefend boolean is on
	 */
	@Override
	public void apply(IActions actions){
			if(this.isEnabled()){
				defend(actions);
			}
	}
	public abstract void defend(IActions actions);

	public Health getHealth() {
		return health;
	}
}