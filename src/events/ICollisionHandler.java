package events;

import collisions.Collision;
import level.LevelProperties;

public interface ICollisionHandler {
	
	public void applyCollision(Collision one, Collision two, LevelProperties levelProperties);

}
