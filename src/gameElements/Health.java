package gameElements;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Health {

	private DoubleProperty value;
	private BooleanProperty isMortal;

	public Health() {
		isMortal = new SimpleBooleanProperty();
		isMortal.set(false);
	}

	public Health(double myHealth) {
		value = new SimpleDoubleProperty();
		value.set(myHealth);
		isMortal = new SimpleBooleanProperty();
		isMortal.set(true);
	}

	private void changeHealth(double val) {
		if (isMortal.getValue()) {
			value.add(val);
		}
	}

	public void setHealth(double health) {
		this.value.set(health);
		isMortal.set(true);
	}

	public double getHealthValue() {
		return value.doubleValue();
	}

	public void decrementHealth(double damage) {
		changeHealth(damage * -1);
	}

	public void incrementHealth(double val) {
		changeHealth(val);
	}

	public boolean isDead() {
		if (!isMortal.getValue()) {
			return false;
		}
		return value.doubleValue() <= 0;
	}
}