package gameElements;

public abstract class Collision implements Behavior{

	@Override
	public void apply(Sprite sprite) {
		Class collision = sprite.getCollision().getClass();
		
		handleCollision(sprite.getCollision());
	}
	
	public abstract void handleCollision(Collision collision);
}
