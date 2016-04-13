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
		if(!isMortal){
			value.add(val);
		}
	}
	public void setHealth(DoubleProperty health){
		this.value = health;
		isMortal = true;
	}
	public DoubleProperty getHealth(){
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
		return value.doubleValue() <= 0;
	}
}
