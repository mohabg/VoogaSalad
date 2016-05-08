// This entire file is part of my masterpiece.
// Tavo Loaiza
package behaviors;

import gameElements.CircularQueue;
import gameElements.ISpriteProperties;
import gameElements.Sprite;
import gameElements.Vector;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import behaviors.Movement;

public class PatternMovement extends Movement {
	protected static final int DEFAULT_PERIOD = 10;
	protected int callCount;
	protected IntegerProperty period;
	CircularQueue<Vector> pattern;

	public PatternMovement() {
		super();
		init();

	}

	public PatternMovement(double value) {
		super(value);
		init();

	}

	public void init(){
		pattern = new CircularQueue<Vector>();
		callCount = 0;
		period= new SimpleIntegerProperty(0);
	}


	public void addVector(Vector vect){
		pattern.add(vect);

	}

	public void removeLastVector(){
		pattern.removeLast();
	}

	public void clearPattern(){
		pattern = new CircularQueue<Vector>();
	}

	@Override
	public void move(IActions actions) {
		ISpriteProperties properties = actions.getSpriteProperties();
		callCount ++;
		if(callCount % period.intValue() == 0){
			Vector nextVector = pattern.getNext();
			properties.setXVel(nextVector.getxVel());
			properties.setYVel(nextVector.getyVel());
		}
		else{
				properties.setXVel(0);
				properties.setYVel(0);
			}

		properties.setX(properties.getX() + properties.getXVel());
		properties.setY(properties.getY() + properties.getYVel());
	}

	public IntegerProperty getPeriod() {
		return period;
	}

	public void setPeriod(IntegerProperty period) {
		this.period = period;
	}

	@Override
	public Behavior getClone() {
		return new PatternMovement(this.getValue());
	}
}
