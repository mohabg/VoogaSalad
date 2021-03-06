package gameElements;


/**
 * Keeps track of the time of each level. Used by the level class to display the time in the game. 
 * @see level
 * 
 * @author gauravkumar
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Time {
	
	public static DoubleProperty DEFAULT_DELAY = new SimpleDoubleProperty();
	
	private DoubleProperty myDelay;
	private DoubleProperty myCurrentTime;

	public Time() {
		DEFAULT_DELAY.set(0.017);
		myDelay = new SimpleDoubleProperty();
		myDelay.set(DEFAULT_DELAY.doubleValue());
		myCurrentTime = new SimpleDoubleProperty();
	}
	
	public Time(int initialTime) {
		myDelay = DEFAULT_DELAY;
		myCurrentTime.set(initialTime);
	}
	
	public double getTime() {
		return myCurrentTime.doubleValue();
	}
	
	public void updateTime() {
		//myCurrentTime -= myDelay;
		myCurrentTime.set(myCurrentTime.doubleValue() - myDelay.doubleValue());
		timeToString();
	}
	
	public void timeToString() {
		int minutes = (int) myCurrentTime.doubleValue()/60;
		int seconds = (int) myCurrentTime.doubleValue()%60;
		//System.out.println("Time Remaining: " + Integer.toString(minutes) + ":" + Integer.toString(seconds));
	}
	
	public double getMyDelay() {
		return myDelay.doubleValue();
	}

	public void setMyDelay(DoubleProperty myDelay) {
		this.myDelay = myDelay;
	}
	public double getMyCurrentTime() {
		return myCurrentTime.doubleValue();
	}
	
	public DoubleProperty getMyTime(){
		return myCurrentTime;
	}

	public void setMyCurrentTime(DoubleProperty myCurrentTime) {
		this.myCurrentTime = myCurrentTime;
	}

	public DoubleProperty getMyCurrentTimeProperty(){
		return myCurrentTime;
	}

	
}
