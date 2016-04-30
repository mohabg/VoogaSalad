package gameElements;

import java.util.List;
import java.util.Map;

import behaviors.Behavior;
import behaviors.IActions;
import collisions.Collision;
import collisions.DamageCollision;
import events.Executable;
import gameplayer.SpriteFactory;
import javafx.beans.property.MapProperty;
import javafx.scene.input.KeyCode;

public interface ISprite{
	
	public enum spriteState{
		User,
		Enemy,
		Obstacle,
		Offense,
		Defense
	}
	
	public void update(IActions actions);

	public String getMyRef();

	public List<Collision> getCollisions();

	public void takeDamage(double criticalHitDamage);

	public void kill();

	public Map<String, Behavior> getBehaviors();
	
	public ISpriteProperties getSpriteProperties();

	public boolean isUserControlled();

	public MapProperty<KeyCode, Executable> getUserPressBehaviors();

	public boolean isDead();

	public boolean isOutOfBounds();

	public void setUserControlled(boolean userAction);

	public Health getMyHealth();

	public void addCollision(Collision damageCollision);
	

	public ISprite clone();
}
