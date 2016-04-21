package behaviors;

import gameElements.CircularQueue;
import gameElements.Sprite;
import gameElements.Vector;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PatternMovement extends Movement {
	protected static final int DEFAULT_PERIOD = 10;
	protected IntegerProperty callCount;
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
        callCount = new SimpleIntegerProperty();
        period = new SimpleIntegerProperty();
		callCount.set(0);
		period.set(DEFAULT_PERIOD);
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
	public void move(Sprite sprite) {
		callCount.add(1);
		if(callCount.intValue() % period.intValue() == 0){
			Vector nextVector = pattern.getNext();
			sprite.getSpriteProperties().setMyXvel(nextVector.getxVel());
			sprite.getSpriteProperties().setMyYvel(nextVector.getyVel());
		}
		else{
			sprite.getSpriteProperties().setMyXvel(0);
			sprite.getSpriteProperties().setMyYvel(0);
		}
	}

}
