package gameElements;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Score {

	private IntegerProperty scoreValue;
	
	public Score(){
		scoreValue = new SimpleIntegerProperty(0);
	}
	public Score(Integer score){
		this();
		this.scoreValue.set(score);
	}
	public IntegerProperty getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(IntegerProperty scoreValue) {
		this.scoreValue = scoreValue;
	}
	public void addScore(int increment){
		scoreValue.setValue(scoreValue.getValue()+increment);
	}
	public void subtractScore(int decrement){
		scoreValue.setValue(scoreValue.getValue()-decrement);
	}
	public void setScoreValue(Integer currentPoints) {

		IntegerProperty score=new SimpleIntegerProperty(currentPoints);
		this.scoreValue=score;
	}
}
