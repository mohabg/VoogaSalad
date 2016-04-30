package collisions;

import behaviors.IActions;
import gameElements.ISprite;
import level.LevelProperties;

public class BounceCollision extends Collision{

	public BounceCollision(){
		super();
	}
	public BounceCollision(double value){
		super(value);
	}
	
	public void handleCollision(ActorCollision collision, LevelProperties levelProperties){
		ISprite actor = levelProperties.getSpriteForCollision(collision);
		actor.getSpriteProperties().setYVel(getValue());
	}
	
	@Override
	public Collision clone() {
		return new BounceCollision(this.getValue());
	}

}
