package gameElements;

import java.util.List;
import java.util.Map;

import authoringEnvironment.RefObject;
import behaviors.Behavior;
import collisions.Collision;

public interface ISpriteFactory {
	 SpriteMap getSpriteMap();
	 void setSpriteMap(SpriteMap spriteMap);
	 Sprite makeSprite(double x, double y, RefObject myRef);
	 Sprite makeSprite(double x, double y, Health myHealth, List<Collision> myCollisions,
				Map<String, Behavior> myBehaviors, RefObject myRef);
}

// look at update method in sprites
// look @ level
