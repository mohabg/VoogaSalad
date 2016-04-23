package gameElements;


/**
 * Keeps track of the time of each level. Used by the level class to display the time in the game. 
 * @see level
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;


public class Time {
	
	public static double INITIAL_TIME_AMOUNT = 300000;
	public static double DEFAULT_DELAY = 1000;
	
	private double myDelay;
	private double myInitialTime;
	private double myCurrentTime;

	public Time() {
		myDelay = DEFAULT_DELAY; //in milliseconds --> 1 second
		myInitialTime = INITIAL_TIME_AMOUNT;
		myCurrentTime = INITIAL_TIME_AMOUNT;
	}
	
	public Time(int initialTime) {
		myDelay = DEFAULT_DELAY; //in milliseconds --> 1 second
		myInitialTime = initialTime;
		myCurrentTime = INITIAL_TIME_AMOUNT;
	}
	
	public double getTime() {
		return myCurrentTime;
	}
	
	public void updateTime() {
		myCurrentTime -= myDelay;
		timeToString();
	}
	
	public void timeToString() {
		System.out.println(Double.toString(myCurrentTime));
	}
	
}
