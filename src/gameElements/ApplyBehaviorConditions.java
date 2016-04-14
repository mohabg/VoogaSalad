package gameElements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ApplyBehaviorConditions {

	
	private DoubleProperty probability;
	private IntegerProperty frameDelay;
	private DoubleProperty distFromUser;
	private IntegerProperty framesPassed;
	private DoubleProperty maxDuration;
	private DoubleProperty currentDuration;
	
	public ApplyBehaviorConditions(){
		probability = new SimpleDoubleProperty(0);
		frameDelay = new SimpleIntegerProperty(0);
		distFromUser = new SimpleDoubleProperty(0);
		framesPassed = new SimpleIntegerProperty(0);
		maxDuration = new SimpleDoubleProperty(0);
		currentDuration = new SimpleDoubleProperty(0);
	}
	public ApplyBehaviorConditions(double probability, int frameDelay, double distFromUser, double maxDuration){
		this();
		this.probability.set(probability);
		this.frameDelay.set(frameDelay);
		this.distFromUser.set(distFromUser);
		this.maxDuration.set(maxDuration);
	}
	public double getCurrentDuration() {
		return currentDuration.doubleValue();
	}
	public void setCurrentDuration(double currentDuration) {
		this.currentDuration.set(currentDuration);
	}
	public double getMaxDuration() {
		return maxDuration.doubleValue();
	}
	public void setMaxDuration(DoubleProperty duration) {
		this.maxDuration = duration;
	}
	public double getProbability() {
		return probability.doubleValue();
	}
	public void setProbability(DoubleProperty probability) {
		this.probability = probability;
	}
	public int getFrameDelay() {
		return frameDelay.get();
	}
	public void setFrameDelay(int frameDelay) {
		this.frameDelay.set(frameDelay);
	}
	public double getDistFromUser() {
		return distFromUser.doubleValue();
	}
	public void setDistFromUser(double distFromUser) {
		this.distFromUser.set(distFromUser);
	}
	public int getFramesPassed() {
		return framesPassed.get();
	}
	public void setFramesPassed(int framesPassed) {
		this.framesPassed.set(framesPassed);
	}
}
