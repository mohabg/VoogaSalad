package gameElements;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A class that describes the health of each sprite, which usually determines when a sprite will disappear from a level and be
 * removed from spriteMap. Most classes use this class to check and update health of certain sprites on the screen 
 */


public class Health {

	
	private DoubleProperty healthValue;
	private BooleanProperty isMortal;	
	
	public Health(){
		healthValue = new SimpleDoubleProperty(0);
		isMortal = new SimpleBooleanProperty(false);
	}
	
	public Health(double myHealth){
		healthValue = new SimpleDoubleProperty(myHealth);
		isMortal = new SimpleBooleanProperty(true);
	}

	private void changeHealth(double val) {
		if (isMortal.getValue()) {
			healthValue.set(healthValue.doubleValue() + val);
		}
	}

	public void setHealth(double health) {
		this.healthValue.set(health);
		isMortal.set(true);
	}

	public double getHealthValue() {
		return healthValue.doubleValue();
	}

	public void decrementHealth(double damage) {
		changeHealth(damage * -1);
	}

	public void incrementHealth(double val) {
		changeHealth(val);
	}
	/**
	 * If the sprite is mortal, check if its health is zero
	 * @return
	 */
	public boolean isDead() {
		if (!isMortal.getValue()) {
			return false;
		}
		return healthValue.doubleValue() <= 0;
	}
}