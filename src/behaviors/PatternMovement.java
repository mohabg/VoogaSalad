package behaviors;

import gameElements.CircularQueue;
import gameElements.Sprite;
import gameElements.Vector;
import behaviors.Movement;

public class PatternMovement extends Movement {
	protected static final int DEFAULT_PERIOD = 10;
	protected int callCount;
	protected int period;
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
		period = DEFAULT_PERIOD;
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
		// TODO Auto-generated method stub
		
	}


/*
	@Override
	public void move(Sprite sprite) {
		callCount ++;
		if(callCount % period == 0){
			Vector nextVector = pattern.getNext();
			sprite.getSpriteProperties().setMyXvel(nextVector.getxVel());
			sprite.getSpriteProperties().setMyYvel(nextVector.getyVel());
		}
		else{
			sprite.getSpriteProperties().setMyXvel(0);
			sprite.getSpriteProperties().setMyYvel(0);
		}
	}
*/
}
