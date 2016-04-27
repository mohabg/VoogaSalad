package events;

import collisions.Collision;
import gameElements.SpriteMap;
import level.LevelProperties;

public interface ICollisionHandler {
	
	public void applyCollision(Collision one, Collision two, LevelProperties levelProperties);
	public void checkCollisions(LevelProperties levelProperties);

}
