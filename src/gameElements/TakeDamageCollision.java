package gameElements;

public class TakeDamageCollision extends Collision{
	private void handleCollision(DamageCollision collision){
		collision.handleCollision(this);
	}
	

}
