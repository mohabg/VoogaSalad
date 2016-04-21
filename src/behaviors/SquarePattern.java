package behaviors;

import gameElements.Vector;

public class SquarePattern extends PatternMovement {

	private static final double DEFAULT_SIZE = 5;

	public SquarePattern() {
		super();
		setSquare(DEFAULT_SIZE,DEFAULT_PERIOD);
	}

	public SquarePattern(double size){
		super(size);
		setSquare(size, DEFAULT_PERIOD);
		callCount.set(0);
	}

	public SquarePattern(double size, int period) {
		super(size);
		setSquare(size, period);
		callCount.set(0);
	}

	public void setSquare(double size, int period){
		setSize(size);
		this.period.set(period);
	}

	public void setSize(double size){
		clearPattern();
		addVector(new Vector(size,0));
		addVector(new Vector(0,(-1)*size));
		addVector(new Vector((-1)*size,0));
		addVector(new Vector(0,size));
	}


}
