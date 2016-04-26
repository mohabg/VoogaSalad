package gameElements;

import behaviors.IActions;
import gameplayer.SpriteFactory;

public interface ISprite {
	
	public enum spriteState{
		User,
		Enemy,
		Obstacle,
		Offense,
		Defense
	}
	
	void update(IActions actions);
}
