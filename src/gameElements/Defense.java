package gameElements;

public abstract class Defense extends Sprite implements Behavior{
	private Health myHealth;
	
	public Defense(){
        super("hi");
		myHealth=new Health();
	}
	public Defense(Health health){
        super("hi");
        myHealth=health;
	}
	
	public Health getHealth() {
		return this.myHealth;
	}
	public void setHealth(Health health) {
		this.myHealth = health;
	}
	
}
