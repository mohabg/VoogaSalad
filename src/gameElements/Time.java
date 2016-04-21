package gameElements;


/**
 * Keeps track of the time of each level. Used by the level class to display the time in the game. 
 * @see level
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;


public class Time {
	
	private IntegerProperty myDelay;
	private Timer myTimer;
	private IntegerProperty myInitialTime;

	public Time() {
        myDelay = new SimpleIntegerProperty();
        myInitialTime = new SimpleIntegerProperty();
		myDelay.set(1000); //in milliseconds --> 1 second
		myTimer = new Timer();
		myInitialTime.set(0);
	}
	
	public Time(int initialTime) {
		myDelay.set(1000); //in milliseconds --> 1 second
		myTimer = new Timer();
		myInitialTime.set(initialTime);
	}
	
	public void startTime() {
		if ( myInitialTime.getValue() == 0)
			runRegularTimer();
		else
			runCountdownTimer(myInitialTime.getValue());
	}
	
	private void runCountdownTimer(int initialTime) {
		CountdownTimerTask cdTimerTask = new CountdownTimerTask(initialTime);
		myTimer.schedule(cdTimerTask, 0, myDelay.getValue());
	}

	private void runRegularTimer() {
		TimerTask task = new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				count++;
				int min = count/60;
			    int sec = count%60;
			}
		};
		myTimer.schedule(task, myDelay.getValue());
	}
	
	public void pauseTime() {
		//TODO: look into pausing and resuming TimerTasks
		myTimer.cancel();
	}
	
	public void resetTime() {
		myTimer.cancel();
		myTimer = new Timer();
		startTime();
	}
	
	public void setScore() {
		//TODO: once score is implemented, update here
	}
	
}
