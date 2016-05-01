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
import level.ILevelProperties;
import level.LevelProperties;

/**
 * Describes attacking behavior of Sprites. Has the amount of ammunition(IntegerProperty ammunition), how long the 
 * bullets take to charge(IntegerProperty chargeTime), and ApplyBehavior conditions, which has attributes that help
 * determine whether an enemy sprite is eligible to shoot.
 * @see ExecuteConditions
 * @author mohabgabal
 */


public abstract class Attack extends Behavior {
	
	private IntegerProperty ammunition;
	private Movement movement;
	private RefObject myRef;
	@IgnoreField
	private boolean shotOnce;
	
	public Attack() {
		this(new RefObject());
		shotOnce = false;
	}
	
	public Attack(RefObject myRef){
		this(myRef, 1 , new MoveVertically());
	}

	public Attack(RefObject myRef, int ammunition, Movement movement) {
		super();
		this.ammunition = new SimpleIntegerProperty(ammunition);
		this.movement = movement;
		this.myRef = myRef;
	}
	
	@Override
	public void execute(IActions actions, ILevelProperties levProps){
		if(!shotOnce){
			shotOnce = true;
			super.execute(actions, levProps);
		}
	}
	
	@Override
	public void stop(IActions actions, ILevelProperties levProps){
		shotOnce = false;
	}
	
	@Override
	public void apply(IActions actions){
		if(this.shotOnce){
			shoot(actions);
		}
	}
	public abstract void shoot(IActions actions);
    

	public RefObject getMyRef(){
		return myRef;
	}

	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
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