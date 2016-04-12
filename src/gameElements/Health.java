package gameElements;

public class Health {
	
	private double value;
	private boolean isMortal;
	
	public Health(){
		isMortal = false;
	}
	public Health(double myHealth){
		this.value = myHealth;
		isMortal = true;
	}
	private void changeHealth(double val){
		if(!isMortal){
			value += val;
		}
	}
	public void setHealth(double health){
		this.value = health;
		isMortal = true;
	}
	public double getHealth(){
		return value;
	}
	public void decrementHealth(double val){
		changeHealth(val * -1);
	}
	public void incrementHealth(double val){
		changeHealth(val);
	}
	public boolean isDead(){
		if(!isMortal){
			return false;
		}
		return value <= 0;
	}
}
