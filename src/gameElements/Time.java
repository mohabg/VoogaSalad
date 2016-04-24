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
	
	public static double INITIAL_TIME_AMOUNT = 300;
	public static double DEFAULT_DELAY = 0.017;
	
	private double myDelay;
	private double myInitialTime;
	private double myCurrentTime;

	public Time() {
		myDelay = DEFAULT_DELAY;
		myInitialTime = INITIAL_TIME_AMOUNT;
		myCurrentTime = INITIAL_TIME_AMOUNT;
	}
	
	public Time(int initialTime) {
		myDelay = DEFAULT_DELAY;
		myInitialTime = initialTime;
		myCurrentTime = initialTime;
	}
	
	public double getTime() {
		return myCurrentTime;
	}
	
	public void updateTime() {
		myCurrentTime -= myDelay;
		timeToString();
	}
	
	public void timeToString() {
		int minutes = (int) myCurrentTime/60;
		int seconds = (int) myCurrentTime%60;
		System.out.println("Time Remaining: " + Integer.toString(minutes) + ":" + Integer.toString(seconds));
	}
	
}
