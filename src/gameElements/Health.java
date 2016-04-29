package gameElements;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import voogasalad.util.hud.source.Property;

/**
 * A class that describes the health of each sprite, which usually determines when a sprite will disappear from a level and be
 * removed from spriteMap. Most classes use this class to check and update health of certain sprites on the screen 
 */


public class Health {

	
	private DoubleProperty healthValue;
	private Property healthProperty;
	private BooleanProperty isMortal;	
	
	public Health(){
		healthValue = new SimpleDoubleProperty(0);
		isMortal = new SimpleBooleanProperty(false);
		healthProperty = new Property(healthValue, "Health");
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
	public Property getHealthProperty(){
		return this.healthProperty;
	}
	public void takeDamage(double damage) {
		changeHealth(damage * -1);
	}

	public void incrementHealth(double val) {
		changeHealth(val);
	}
	
	public void kill(){
		isMortal.set(true);
		healthValue.set(0);
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
	public DoubleProperty getProperty(){
		return healthValue;
	}
	
	public void setMortal(boolean bool){
		this.isMortal.set(bool);
	}
	
	public Health getClone() {
		Health clone = new Health();
		clone.setHealth(this.getHealthValue());
		clone.setMortal(this.isMortal.get());
		return clone;
	}
}