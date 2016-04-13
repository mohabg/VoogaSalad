package gameElements;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Health {
	
	private DoubleProperty value = new SimpleDoubleProperty();
	private BooleanProperty isMortal = new SimpleBooleanProperty();
	
	public Health(){
		isMortal.set(false);
	}
	public Health(DoubleProperty myHealth){
		this.value = myHealth;
		isMortal.set(true);
	}
	private void changeHealth(double val){
		if(isMortal.getValue().booleanValue()){
			value.add(val);
		}
	}
	public void setHealth(DoubleProperty health){
		this.value = health;
		isMortal.set(true);
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
		if(!isMortal.getValue().booleanValue()){
			return false;
		}
		return value.doubleValue() <= 0;
	}
}
