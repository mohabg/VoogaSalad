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
	
	public void update(IActions actions);
}
