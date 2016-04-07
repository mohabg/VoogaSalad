package gameElements;

public class DamageCollision extends Collision{
	
	private double damage;
	
	public void setDamage(double damage){
		this.damage = damage;
	}
	public double getDaamage(){
		return damage;
	}
	private void handleCollision(TakeDamageCollision collision){
		Sprite spriteToDamage = collision.getSprite();
		spriteToDamage.getHealth().decrementHealth(damage);
	}
}
