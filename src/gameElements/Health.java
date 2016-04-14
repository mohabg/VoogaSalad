package gameElements;

import javafx.beans.property.DoubleProperty;

public class Health {
	
	private DoubleProperty value;
	private boolean isMortal;
	
	public Health(){
		isMortal = false;
	}
	public Health(DoubleProperty myHealth){
		this.value = myHealth;
		isMortal = true;
	}
	private void changeHealth(double val){
		if(isMortal){
			value.add(val);
		}
	}
	public void setHealth(double health){
		this.value.set(health);
		isMortal = true;
	}
	public double getHealthValue(){
		return value.doubleValue();
	}
	public void decrementHealth(double damage){
		changeHealth(damage * -1);
	}
	public void incrementHealth(double val){
		changeHealth(val);
	}
	public boolean isDead(){
		if(!isMortal){
			return false;
		}
		return value.doubleValue() <= 0;
	}
}
