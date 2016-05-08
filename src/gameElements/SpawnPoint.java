package gameElements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SpawnPoint {
	private int x;
	private int y;
	private DoubleProperty probability;
	
	public SpawnPoint(){
		this.x = 0;
		this.y = 0;
		this.probability = new SimpleDoubleProperty();
	}

	public SpawnPoint(int x, int y, DoubleProperty probability) {
		super();
		this.x = x;
		this.y = y;
		this.probability = probability;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getProbability() {
		return probability.doubleValue();
	}

	public void setProbability(DoubleProperty probability) {
		this.probability = probability;
	}
}
