package gameElements;

import authoringEnvironment.settingsWindow.ObjectEditorFactory.Annotations.IgnoreField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Has information and methods to determine whether a sprite is eligible to defend or shoot at a given time. Probability describes
 * the probability that a sprite(usually an automated one) will shoot, the framedelay will determine how often a sprite is supposed to 
 * do a certain behavior. distFromUser describes how far an automated sprite is from the user, which may be useful(depending on the settings)
 * on whether the sprite is eligible to do a certain behavior. Durations descrive how long the behavior is supposed to last. This is used
 * for enemy behaviors. 
 */


public class ExecuteConditions {

	
	private DoubleProperty probability;
	@IgnoreField
	private DoubleProperty distFromUser;
	private DoubleProperty minDistFromUser;
	private DoubleProperty maxDistFromUser;
	private DoubleProperty frequency; //In seconds
	private DoubleProperty maxDuration;
	@IgnoreField
	private DoubleProperty currentDuration;
	@IgnoreField
	private double runningTime;
	@IgnoreField
	private BooleanProperty enabled;
	
	public ExecuteConditions(){
		probability = new SimpleDoubleProperty(0);
		distFromUser = new SimpleDoubleProperty(0);
		minDistFromUser = new SimpleDoubleProperty(0);
		maxDistFromUser = new SimpleDoubleProperty(0);
		maxDuration = new SimpleDoubleProperty(0);
		currentDuration = new SimpleDoubleProperty(0);
		frequency = new SimpleDoubleProperty(0);
		runningTime = System.currentTimeMillis();
		enabled = new SimpleBooleanProperty(false);
	}
	

	public boolean ready(Sprite sprite){
		if(sprite.isUserControlled()){
			return this.isEnabled();
		}
		else {
			return isAIReady();
		}
	}
	public boolean isAIReady() {
		if (Math.random() < getProbability()) {
			double elapsedTime = System.currentTimeMillis() - runningTime;
			if (elapsedTime >= frequency.doubleValue() * 1000) {
				runningTime = System.currentTimeMillis();
				if (getDistFromUser() >= getMinDistFromUser() && getDistFromUser() <= getMaxDistFromUser()) {
					return true;
				}
			}
		}
		return false;
	}
	public double getMinDistFromUser(){
		return minDistFromUser.doubleValue();
	}
	public double getMaxDistFromUser(){
		return maxDistFromUser.doubleValue();
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
	public void setMaxDuration(double duration) {
		this.maxDuration.set(duration);
	}
	public double getProbability() {
		return probability.doubleValue();
	}
	public void setProbability(double probability) {
		this.probability.set(probability);
	}
	public double getDistFromUser() {
		return distFromUser.doubleValue();
	}
	public void setDistFromUser(double distFromUser) {
		this.distFromUser.set(distFromUser);
	}
	public void setFrequency(double frequency){
		this.frequency.set(frequency);
	}
	public double getFrequency(){
		return frequency.doubleValue();
	}
	public boolean isEnabled(){
		return enabled.getValue();
	}
	public void enable(){
		enabled.set(true);
	}
	public void disable(){
		enabled.set(false);
	}


	public double getRunningTime() {
		return runningTime;
	}


	public void setRunningTime(double runningTime) {
		this.runningTime = runningTime;
	}


	public void visit(AIController aiController){
		
	}
}
