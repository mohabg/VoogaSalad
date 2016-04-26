package gameElements;

import gameplayer.SpriteFactory;

public interface ISprite {
	
	public enum spriteState{
		User,
		Enemy,
		Obstacle,
		Offense,
		Defense
	}
	
	void update(SpriteFactory spriteFactory);
}
