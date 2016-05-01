package gameElements;

import java.util.List;
import java.util.Map;

import behaviors.Behavior;
import behaviors.IActions;
import behaviors.Movement;
import collisions.Collision;
import collisions.DamageCollision;
import events.Executable;
import gameplayer.SpriteFactory;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import level.LevelProperties;

public interface ISprite{
	
	public enum spriteState{
		User,
		Enemy,
		Obstacle,
		Offense,
		Defense
	}
	
	public void update(IActions actions, LevelProperties levProps);

	public String getMyRef();

	public List<Collision> getCollisions();

	public void takeDamage(double criticalHitDamage);

	public void kill();

	public ISpriteProperties getSpriteProperties();

	public boolean isUserControlled();

	public boolean isDead();

	public boolean isOutOfBounds();

	public void setUserControlled(boolean userAction);

	public void addCollision(Collision damageCollision);

	public Health getMyHealth();

	public void addBehavior(Behavior behavior);
	
	public ListProperty<Behavior> getBehaviors();

	public ExecuteConditions getSpawnConditions();
}
