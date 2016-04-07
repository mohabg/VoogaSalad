package gameElements;

import java.util.Observable;

public class Health extends Observable{
	
	private double value;
	private boolean isMortal;
	
	public Health(){
		isMortal = false;
	}
	public Health(double value){
		this.value = value;
		isMortal = true;
	}
	private void changeHealth(double val){
		if(!isMortal){
			value += val;
			this.notifyObservers();
		}
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
