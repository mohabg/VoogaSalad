package behaviors;

import java.util.ArrayList;
import java.util.List;

import authoringEnvironment.RefObject;
import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import collisions.Collision;
import gameElements.ExecuteConditions;
import gameElements.SpriteProperties;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import level.LevelProperties;

/**
 * Describes attacking behavior of Sprites. Has the amount of ammunition(IntegerProperty ammunition), how long the 
 * bullets take to charge(IntegerProperty chargeTime), and ApplyBehavior conditions, which has attributes that help
 * determine whether an enemy sprite is eligible to shoot.
 * @see ExecuteConditions
 */


public abstract class Attack extends Behavior {
	
	private IntegerProperty ammunition;
	private IntegerProperty chargeTime;
	private Movement movement;
	private List<Collision> collisions;
	private RefObject myRef;
	//private SpriteProperties target;
	@IgnoreField
	private boolean shotOnce;
	
	public Attack() {
		this(new RefObject());
		shotOnce = false;
	}
	
	public Attack(RefObject myRef){
		this(myRef, 1 ,0, new MoveVertically(), new ArrayList<>());
	}

	public Attack(RefObject myRef, int ammunition, int chargeTime, Movement movement, List<Collision> collisions) {
		super();
		this.ammunition = new SimpleIntegerProperty(ammunition);
		this.chargeTime = new SimpleIntegerProperty(chargeTime);
		this.movement = movement;
		this.myRef = myRef;
		this.collisions = collisions;
	}
	@Override
	public void execute(IActions actions, LevelProperties levProps){
		if(!shotOnce){
			shotOnce = true;
			super.execute(actions, levProps);
		}
	}
	@Override
	public void stop(IActions actions, LevelProperties levProps){
		shotOnce = false;
	}
	@Override
	public void apply(IActions actions, LevelProperties levProps){
		if(this.shotOnce){
			shoot(actions, levProps);
		}
	}
	
	public abstract void shoot(IActions actions, LevelProperties levProps);
    

	public RefObject getMyRef(){
		return myRef;
	}

	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	public List<Collision> getCollisions() {
		return collisions;
	}

	public void setCollisions(List<Collision> collisions) {
		this.collisions = collisions;
	}

	public int getChargeTime() {
		return chargeTime.get();
	}

	public void setChargeTime(int chargeTime) {
		this.chargeTime.set(chargeTime);
    }

	public int getAmmunition() {
		return ammunition.get();
	}

	public void setAmmunition(int ammunition) {
		this.ammunition.set(ammunition);
    }

	public boolean hasAmmunitionLeft() {
		return ammunition.get() > 0;
	}
}