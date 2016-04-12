package gameElements;

public abstract class Defense extends Sprite implements Behavior{
	private Health myHealth;
	private Collision collision;
	private boolean ready;
	
	public Defense(){
        super("hi");
		myHealth=new Health();
	}
	public Defense(Health health){
        super("hi");
        myHealth=health;
	}
	
	public Collision getCollision() {
		return collision;
	}
	public void setCollision(Collision collision) {
		this.collision = collision;
	}
	
	public Health getHealth() {
		return this.myHealth;
	}
	public void setHealth(Health health) {
		this.myHealth = health;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	
}
