package gameElements;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A class that describes the health of each sprite, which usually determines when a sprite will disappear from a level and be
 * removed from spriteMap. Most classes use this class to check and update health of certain sprites on the screen 
 */


public class Health {

	
	private DoubleProperty healthValue;
	private BooleanProperty isMortal;	
	private IntegerProperty numLives;
	@IgnoreField
	private double originalHealthValue;
	
	public Health(){
		healthValue = new SimpleDoubleProperty(0);
		isMortal = new SimpleBooleanProperty(false);
		numLives = new SimpleIntegerProperty(1);
	}
	
	public Health(double myHealth){
		this();
		healthValue.set(myHealth);
		originalHealthValue = myHealth;
		isMortal.set(true);
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

	public void takeDamage(double damage) {
		changeHealth(damage * -1);
		if(this.healthValue.get() <= 0){
			numLives.set(numLives.get() - 1);
			if(numLives.get() <= 0){
				//end game
			}
			else{
				resetHealth();
			}
		}
	}
	public void addLife(){
		this.numLives.set(numLives.get() + 1);
	}
	private void resetHealth() {
		this.healthValue.set(originalHealthValue);	
	}

	public void incrementHealth(double val) {
		changeHealth(val);
	}
	
	public void kill(){
		isMortal.set(true);
		healthValue.set(0);
		numLives.set(0);
	}
	/**
	 * If the sprite is mortal, check if its health is zero
	 * @return
	 */
	public boolean isDead() {
		if (!isMortal.getValue()) {
			return false;
		}
		return healthValue.doubleValue() <= 0 && this.numLives.get() <= 0;
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