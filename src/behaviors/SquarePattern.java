// This entire file is part of my masterpiece.
// Tavo Loaiza
package behaviors;

import behaviors.PatternMovement;
import gameElements.Vector;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SquarePattern extends PatternMovement {


	private static final int DEFAULT_SIZE = 5;

	public SquarePattern() {
		super();
		setSquare(DEFAULT_SIZE,DEFAULT_PERIOD);
	}

	public SquarePattern(double size){
		super(size);
		setSquare(size, DEFAULT_PERIOD);
		callCount = 0;
	}

	public SquarePattern(double size, int period) {
		super(size);
		setSquare(size, period);
		callCount = 0;
	}

	public void setSquare(double size, int period){
		setSize(size);
		this.period = new SimpleIntegerProperty(period);
	}

	public void setSize(double size){
		clearPattern();
		addVector(new Vector(size,0));
		addVector(new Vector(0,(-1)*size));
		addVector(new Vector((-1)*size,0));
		addVector(new Vector(0,size));
	}


}
