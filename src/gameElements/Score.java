package gameElements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {

	private DoubleProperty scoreValue;
	
	public Score(){
		scoreValue = new SimpleDoubleProperty(0);
	}
	public Score(Integer score){
		this();
		this.scoreValue.set(score);
	}
	public DoubleProperty getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(DoubleProperty scoreValue) {
		this.scoreValue = scoreValue;
	}
	public void addScore(int increment){
		scoreValue.setValue(scoreValue.getValue()+increment);
	}
	public void subtractScore(int decrement){
		scoreValue.setValue(scoreValue.getValue()-decrement);
	}
	public void setScoreValue(Integer currentPoints) {
        this.scoreValue.set(currentPoints);
	}
	
}
